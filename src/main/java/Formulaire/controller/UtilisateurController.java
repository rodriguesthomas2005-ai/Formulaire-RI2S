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
import Formulaire.DTO.InscriptionRequest.UpgradeNonProDTO;
import Formulaire.DTO.InscriptionRequest.UpgradeProDTO;
import Formulaire.entity.Role;
import Formulaire.entity.Utilisateur;
import Formulaire.repository.UtilisateurRepository;
import Formulaire.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UtilisateurController {

    @Autowired private UtilisateurService utilisateurService;
    @Autowired private UtilisateurRepository utilisateurRepository;

    @Operation(summary = "Inscrire un nouvel utilisateur avec ses profils et sa demande d'expérimentation")
    @PostMapping("/inscription")
    public ResponseEntity<?> inscrire(@RequestBody InscriptionRequest request) {
        return ResponseEntity.ok(utilisateurService.inscrireUtilisateur(
            request.getUtilisateur(), request.getProfilPro(), request.getProfilNonPro(), request.getDemandeExpe(), request.getPersonneContactIndustriel()));
    }

    @Operation(summary = "Mettre à jour un utilisateur pour lui ajouter le profil professionnel")
    @PostMapping("/{id}/profil-pro")
    public ResponseEntity<?> upgradeToPro(@PathVariable Long id, @RequestBody UpgradeProDTO dto) {
        // On passe le DTO au service pour la transformation
        return ResponseEntity.ok(utilisateurService.ajouterProfilPro(id, dto));
    }
    
    @Operation(summary = "Ajouter un profil non pro à un utilisateur déjà inscrit")
    @PostMapping("/{id}/profil-non-pro")
    public ResponseEntity<?> upgradeToNonPro(@PathVariable Long id, @RequestBody UpgradeNonProDTO dto) {
        // Transformer le DTO en entité dans le service
        return ResponseEntity.ok(utilisateurService.ajouterProfilNonPro(id, dto));
    }

    @Operation(summary = "Ajouter le profil contact industriel via JSON")
    @PostMapping("/{id}/profil-industriel")
    public ResponseEntity<?> upgradeToIndustriel(
            @PathVariable Long id, 
            @RequestBody InscriptionRequest.ContactIndustrielDTO dto) {
        
        // On passe les données du DTO au service
        return ResponseEntity.ok(utilisateurService.ajouterContactIndustriel(
            id, 
            dto.getFonction(), 
            dto.getEmailPersContact(), 
            dto.getTelephonePersContact()
        ));
    }

@Operation(summary = "Ajouter une mission à un utilisateur avec rattachement au groupe (Dossier)")
@PostMapping("/{id}/inscriptions")
public ResponseEntity<?> ajouterMission(
        @PathVariable Long id, 
        @RequestParam Long idExpe, 
        @RequestParam Role role,
        @RequestParam(required = false) Long idDossier) { // ID du groupe à rejoindre (null pour Senior)
    
    try {
        return ResponseEntity.ok(utilisateurService.ajouterMissionAUtilisateur(id, idExpe, role, idDossier));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
    
    @Operation(summary = "Vérifier l'existence d'un utilisateur à partir de son nom, prénom et date de naissance")
    @GetMapping("/verification")
    public ResponseEntity<?> verifier(@RequestParam String nom, @RequestParam String prenom, @RequestParam String typeutilisateur,
                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateNaissance) {
        Optional<Utilisateur> user = utilisateurService.verifierExistence(nom, prenom, dateNaissance);
        if (user.isPresent()){
            if ("profilpro".equalsIgnoreCase(typeutilisateur) && user.get().getProfilPro() == null) {
                return ResponseEntity.ok(Map.of("existe", false, "id", user.get().getIdUtilisateur()));
            }
            else if ("profilnonpro".equalsIgnoreCase(typeutilisateur) && user.get().getProfilNonPro() == null) {
                return ResponseEntity.ok(Map.of("existe", false, "id", user.get().getIdUtilisateur()));
            }
            else if (!"profilpro".equalsIgnoreCase(typeutilisateur) && !"profilnonpro".equalsIgnoreCase(typeutilisateur)) {
                return ResponseEntity.ok(Map.of("existe", false, "id", user.get().getIdUtilisateur()));
            }
            return ResponseEntity.ok(Map.of("existe", true, "id", user.get().getIdUtilisateur()));
        }
        return ResponseEntity.ok(Map.of("existe", false));
    }

    @Operation(summary = "Lister tous les utilisateurs")
    @GetMapping
    public ResponseEntity<?> lister() {
        return ResponseEntity.ok(utilisateurService.listerTousLesUtilisateurs());
    }


}