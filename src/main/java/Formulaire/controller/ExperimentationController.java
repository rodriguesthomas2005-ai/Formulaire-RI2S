package Formulaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Formulaire.entity.Experimentation;
import Formulaire.service.ExperimentationService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/experimentations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExperimentationController {

    @Autowired
    private ExperimentationService experimentationService;

    @Operation(summary = "Créer une nouvelle expérimentation")
    @PostMapping
    public ResponseEntity<Experimentation> ajouter(@RequestBody Experimentation expe) {
        return ResponseEntity.ok(experimentationService.creerExperimentation(expe));
    }

    @Operation(summary = "Lister toutes les expérimentations")
    @GetMapping
    public ResponseEntity<List<Experimentation>> lister() {
        return ResponseEntity.ok(experimentationService.listerToutesLesExpes());
    }
}