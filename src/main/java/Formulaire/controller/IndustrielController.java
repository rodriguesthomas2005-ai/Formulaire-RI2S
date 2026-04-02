package Formulaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Formulaire.entity.DossierCandidature;
import Formulaire.entity.Fichier;
import Formulaire.entity.Industriel;
import Formulaire.service.IndustrielService;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/industriels")
@CrossOrigin(origins = "*")
public class IndustrielController {

    @Autowired
    private IndustrielService industrielService;

    @GetMapping
    public ResponseEntity<List<Industriel>> listerTousLesIndustriels() {
        return ResponseEntity.ok(industrielService.listerTout());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Industriel> recupererUnIndustriel(@PathVariable Long id) {
        return ResponseEntity.ok(industrielService.trouverParId(id));
    }

    @PostMapping(value = "/inscription/{idUser}/industriel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Industriel> ajouterDossierIndustriel(
            @PathVariable Long idUser,
            @RequestPart("industriel") String industrielJson, 
            @RequestPart("dossierCandidature") String dossierJson,
            @RequestPart("fichier") MultipartFile file) throws Exception {

        // 1. Convertir les Strings JSON en objets Java (via ObjectMapper)
        ObjectMapper mapper = new ObjectMapper();
        Industriel industriel = mapper.readValue(industrielJson, Industriel.class);
        DossierCandidature dossier = mapper.readValue(dossierJson, DossierCandidature.class);

        // 2. Créer l'objet Fichier avec le binaire
        Fichier fichierEntity = new Fichier();
        fichierEntity.setNomFichier(file.getOriginalFilename());
        fichierEntity.setDonnees(file.getBytes()); // On récupère les octets ici !
        fichierEntity.setType(file.getContentType());
        fichierEntity.setTaille(file.getSize() + " bytes");

        // 3. Appeler le service
        Industriel result = industrielService.inscrireIndustriel(industriel, dossier, fichierEntity, idUser);
        return ResponseEntity.ok(result);
    }
}