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

@RestController
@RequestMapping("/api/experimentations")
@CrossOrigin(origins = "*")
public class ExperimentationController {

    @Autowired
    private ExperimentationService experimentationService;

    @PostMapping
    public ResponseEntity<Experimentation> ajouter(@RequestBody Experimentation expe) {
        return ResponseEntity.ok(experimentationService.creerExperimentation(expe));
    }

    @GetMapping
    public ResponseEntity<List<Experimentation>> lister() {
        return ResponseEntity.ok(experimentationService.listerToutesLesExpes());
    }
}