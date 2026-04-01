package Formulaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Formulaire.DTO.InscriptionRequest;
import Formulaire.entity.Industriel;
import Formulaire.service.IndustrielService;

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

    @PostMapping("/inscription/{id}/industriel")
    public ResponseEntity<?> ajouterDossierIndustriel(@RequestBody InscriptionRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(industrielService.inscrireIndustriel(
            request.getIndustriel(), 
            request.getDossierCandidature(), 
            request.getFichier(),
            id
        ));
    }
}