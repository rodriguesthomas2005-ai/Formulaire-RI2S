package Formulaire.controller;

import java.util.Date;
import java.util.List;
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

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins="*")
public class UtilisateurController {

    @Autowired private UtilisateurService utilisateurService;
    @Autowired private UtilisateurRepository utilisateurRepository;

    @PostMapping("/inscription")
    public ResponseEntity<?> inscrire(@RequestBody InscriptionRequest request) {
        return ResponseEntity.ok(utilisateurService.inscrireUtilisateur(
            request.getUtilisateur(), request.getProfilPro(), request.getProfilNonPro(), request.getDemandeExpe()));
    }

    @PostMapping("/{id}/inscriptions")
    public ResponseEntity<?> ajouterMission(@PathVariable Long id, @RequestParam Long idExpe, @RequestParam Role role) {
        return ResponseEntity.ok(utilisateurService.ajouterMissionAUtilisateur(id, idExpe, role));
    }

    @GetMapping("/verification")
    public ResponseEntity<?> verifier(@RequestParam String nom, @RequestParam String prenom, 
                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateNaissance) {
        Optional<Utilisateur> user = utilisateurService.verifierExistence(nom, prenom, dateNaissance);
        if (user.isPresent()) {
            return ResponseEntity.ok(Map.of("existe", true, "id", user.get().getIdUtilisateur()));
        }
        return ResponseEntity.ok(Map.of("existe", false));
    }

    @GetMapping
    public ResponseEntity<?> lister() {
        return ResponseEntity.ok(utilisateurService.listerTousLesUtilisateurs());
    }

    @GetMapping("/simple")
public List<String> listerSimple() {
    return utilisateurRepository.findAll().stream()
           .map(u -> u.getNom() + " " + u.getPrenom())
           .toList();
}
}