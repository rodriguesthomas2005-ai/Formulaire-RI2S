package Formulaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Formulaire.repository.DossierInscriptionExpeRepository;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/dossiers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DossierController {

    @Autowired 
    private DossierInscriptionExpeRepository dossierRepository;

    @Operation(summary = "Récupérer un dossier complet avec ses participants")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenirDossier(@PathVariable Long id) {
        return dossierRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}