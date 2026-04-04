package Formulaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import Formulaire.repository.FichierRepository;
import Formulaire.service.IndustrielService;
import io.swagger.v3.oas.annotations.Operation;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/industriels")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IndustrielController {

    @Autowired
    private FichierRepository fichierRepository;
    @Autowired
    private IndustrielService industrielService;

    @Operation(summary = "Lister tous les industriels inscrits")
    @GetMapping
    public ResponseEntity<List<Industriel>> listerTousLesIndustriels() {
        return ResponseEntity.ok(industrielService.listerTout());
    }

    @Operation(summary = "Optenir les détails d'un industriel spécifique par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<Industriel> recupererUnIndustriel(@PathVariable Long id) {
        return ResponseEntity.ok(industrielService.trouverParId(id));
    }

    @Operation(summary = "Inscrire un industriel avec son dossier et plusieurs fichiers")
    @PostMapping(value = "/inscription/{idUser}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Industriel> ajouterDossierIndustriel(
            @PathVariable Long idUser,
            @RequestPart("industriel") String industrielJson, 
            @RequestPart("dossierCandidature") String dossierJson,
            @RequestPart("fichiers") MultipartFile[] files) throws Exception { // Changement ici : Tableau de fichiers

        // 1. Convertir les Strings JSON en objets Java
        ObjectMapper mapper = new ObjectMapper();
        Industriel industriel = mapper.readValue(industrielJson, Industriel.class);
        DossierCandidature dossier = mapper.readValue(dossierJson, DossierCandidature.class);

        // 2. Boucler sur les fichiers pour créer la liste d'entités
        if (files != null) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    Fichier fichierEntity = new Fichier();
                    fichierEntity.setNomFichier(file.getOriginalFilename());
                    fichierEntity.setDonnees(file.getBytes());
                    fichierEntity.setType(file.getContentType());
                    fichierEntity.setTaille(file.getSize() + " bytes");
                    
                    // Lier le fichier au dossier
                    fichierEntity.setDossier(dossier);
                    // Ajouter à la liste du dossier (pour que Hibernate le voit)
                    dossier.getFichiers().add(fichierEntity);
                }
            }
        }

        // 3. Appeler le service (on passe l'objet industriel qui contient le dossier, qui contient lui-même les fichiers)
        // Note : On n'a plus besoin de passer 'fichierEntity' seul en paramètre
        Industriel result = industrielService.inscrireIndustriel(industriel, dossier, idUser);
        
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Télécharger un fichier associé à un industriel par l'ID du fichier")
    @GetMapping("/fichiers/{id}/download")
    public ResponseEntity<byte[]> recupererFichier(@PathVariable Long id) {
        // 1. Aller chercher le fichier en base de données
        Fichier fichier = fichierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fichier introuvable avec l'id : " + id));

        // 2. Préparer les en-têtes HTTP pour le téléchargement
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fichier.getNomFichier() + "\"")
                .contentType(MediaType.parseMediaType(fichier.getType())) // ex: application/pdf
                .body(fichier.getDonnees()); // On envoie le tableau d'octets (BLOB)
    }
}