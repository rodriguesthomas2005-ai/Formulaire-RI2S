package Formulaire.controller;

import Formulaire.DTO.InscriptionRequest;
import Formulaire.entity.Utilisateur;
import Formulaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "*") 
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/inscription")
    public ResponseEntity<?> inscrire(@RequestBody InscriptionRequest request) {
        try {
            Utilisateur savedUser = utilisateurService.inscrireUtilisateur(
                    request.getUtilisateur(),
                    request.getProfilPro(),
                    request.getProfilNonPro(),
                    request.getDemandeExpe() // <--- AJOUTE JUSTE CETTE LIGNE ICI
            );
            
            return ResponseEntity.ok(savedUser);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Une erreur est survenue lors de l'inscription.");
        }
    }
}