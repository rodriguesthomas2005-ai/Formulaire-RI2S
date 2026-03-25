package Formulaire.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Formulaire.DTO.InscriptionRequest.DemandeExpeDTO;
import Formulaire.entity.DemandeInscriptionExpe;
import Formulaire.entity.Experimentation;
import Formulaire.entity.NonProfessionnel;
import Formulaire.entity.Professionnel;
import Formulaire.entity.Role;
import Formulaire.entity.Statut;
import Formulaire.entity.Utilisateur;
import Formulaire.repository.DemandeInscriptionExpeRepository;
import Formulaire.repository.ExperimentationRepository;
import Formulaire.repository.NonProfessionnelRepository;
import Formulaire.repository.ProfessionnelRepository;
import Formulaire.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class UtilisateurService {

    @Autowired private UtilisateurRepository utilisateurRepository;
    @Autowired private ProfessionnelRepository professionnelRepository;
    @Autowired private NonProfessionnelRepository nonProfessionnelRepository;
    @Autowired private ExperimentationRepository experimentationRepository;
    @Autowired private DemandeInscriptionExpeRepository demandeRepository;


    @Transactional
    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur, Professionnel pro, NonProfessionnel nonPro, DemandeExpeDTO demandeDto) {
        // Sauvegarde de l'utilisateur
        Utilisateur savedUser = utilisateurRepository.save(utilisateur);

        // Liaison des profils
        if (pro != null) { pro.setUtilisateur(savedUser); professionnelRepository.save(pro); }
        if (nonPro != null) { nonPro.setUtilisateur(savedUser); nonProfessionnelRepository.save(nonPro); }

        // Ajout de la première mission si présente
        if (demandeDto != null && demandeDto.getIdExperimentation() != null) {
            ajouterMissionAUtilisateur(savedUser.getIdUtilisateur(), demandeDto.getIdExperimentation(), demandeDto.getRolePourCetteExpe());
        }
        return savedUser;
    }

    // ACTION B : Ajout de mission seule (Utilisateur déjà existant)
    @Transactional
    public DemandeInscriptionExpe ajouterMissionAUtilisateur(Long idUser, Long idExpe, Role role) {
        Utilisateur user = utilisateurRepository.findById(idUser).orElseThrow();
        Experimentation expe = experimentationRepository.findById(idExpe).orElseThrow();

        DemandeInscriptionExpe demande = new DemandeInscriptionExpe();
        demande.setUtilisateur(user);
        demande.setExperimentation(expe);
        demande.setRolePourCetteExpe(role);
        demande.setStatut(Statut.EN_ATTENTE);
        demande.setDateDemande(LocalDateTime.now());

        return demandeRepository.save(demande);
    }

    // Vérification simple pour le Front
    public Optional<Utilisateur> verifierExistence(String nom, String prenom, Date date) {
        return utilisateurRepository.findByNomAndPrenomAndDateNaissance(nom, prenom, date);
    }

    public List<Utilisateur> listerTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

}