package Formulaire.controller;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Formulaire.DTO.InscriptionRequest;
import Formulaire.entity.Role;
import Formulaire.entity.Utilisateur;
import Formulaire.repository.UtilisateurRepository;
import Formulaire.service.UtilisateurService;
import Formulaire.entity.Industriel;
import Formulaire.entity.DossierCandidature;
import Formulaire.entity.Fichier;
import Formulaire.service.IndustrielService;

@RestController
@RequestMapping("/api/industriels")
@CrossOrigin(origins = "*")
public class IndustrielController {

    @Autowired
    private IndustrielService industrielService;

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