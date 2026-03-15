package Formulaire.service;

import Formulaire.DTO.InscriptionRequest.DemandeExpeDTO;
import Formulaire.entity.*;
import Formulaire.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ProfessionnelRepository professionnelRepository;

    @Autowired
    private NonProfessionnelRepository nonProfessionnelRepository;

    // --- NOUVEAUX REPOSITORIES ---
    @Autowired
    private ExperimentationRepository experimentationRepository;

    @Autowired
    private DemandeInscriptionExpeRepository demandeRepository;

    @Transactional
    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur, 
                                           Professionnel profilPro, 
                                           NonProfessionnel profilNonPro, 
                                           DemandeExpeDTO demandeExpe) { 
        

        if (profilPro == null && profilNonPro == null) {
            throw new IllegalArgumentException("Un utilisateur doit avoir au moins un profil (Professionnel ou Non-Professionnel).");
        }

        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);


        if (profilPro != null) {
            profilPro.setUtilisateur(savedUtilisateur);
            professionnelRepository.save(profilPro);
        }

        if (profilNonPro != null) {
            profilNonPro.setUtilisateur(savedUtilisateur);
            nonProfessionnelRepository.save(profilNonPro);
        }

        // Dans UtilisateurService.java
   
    
    // Vérification de sécurité pour le rôle
    // Dans ton bloc if (demandeExpe != null ...) de UtilisateurService
if (demandeExpe != null && demandeExpe.getIdExperimentation() != null) {
    Experimentation expe = experimentationRepository.findById(demandeExpe.getIdExperimentation())
            .orElseThrow(() -> new RuntimeException("Expe introuvable"));

    DemandeInscriptionExpe nouvelleDemande = new DemandeInscriptionExpe();
    nouvelleDemande.setUtilisateur(savedUtilisateur);
    nouvelleDemande.setExperimentation(expe);
    nouvelleDemande.setDateDemande(LocalDateTime.now());
    nouvelleDemande.setStatut(Statut.EN_ATTENTE);

    // --- ON RÉCUPÈRE LE RÔLE ---
    // On s'assure que le DTO nous donne bien un objet de type Role
    nouvelleDemande.setRolePourCetteExpe(demandeExpe.getRolePourCetteExpe());

    demandeRepository.save(nouvelleDemande);
}

        return savedUtilisateur;
    }
}