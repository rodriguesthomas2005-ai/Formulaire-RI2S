package Formulaire.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Formulaire.DTO.InscriptionRequest.DemandeExpeDTO;
import Formulaire.entity.DemandeInscriptionExpe;
import Formulaire.entity.Experimentation;
import Formulaire.entity.NonProfessionnel;
import Formulaire.entity.PersonneContactIndustriel;
import Formulaire.entity.Professionnel;
import Formulaire.entity.Role;
import Formulaire.entity.Statut;
import Formulaire.entity.Utilisateur;
import Formulaire.repository.DemandeInscriptionExpeRepository;
import Formulaire.repository.ExperimentationRepository;
import Formulaire.repository.NonProfessionnelRepository;
import Formulaire.repository.PersonneContactIndustrielRepository;
import Formulaire.repository.ProfessionnelRepository;
import Formulaire.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;

@Service
public class UtilisateurService {

    @Autowired private UtilisateurRepository utilisateurRepository;
    @Autowired private ProfessionnelRepository professionnelRepository;
    @Autowired private NonProfessionnelRepository nonProfessionnelRepository;
    @Autowired private ExperimentationRepository experimentationRepository;
    @Autowired private DemandeInscriptionExpeRepository demandeRepository;
    @Autowired private PersonneContactIndustrielRepository PersonneContactIndustrielRepository;


    @Transactional
    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur, Professionnel pro, NonProfessionnel nonPro, DemandeExpeDTO demandeDto, PersonneContactIndustriel contactIndus) {

        Utilisateur savedUser = utilisateurRepository.save(utilisateur);

        if (pro != null) { pro.setUtilisateur(savedUser); professionnelRepository.save(pro); }
        if (nonPro != null) { nonPro.setUtilisateur(savedUser); nonProfessionnelRepository.save(nonPro); }
        if (contactIndus != null) { contactIndus.setUtilisateur(savedUser); PersonneContactIndustrielRepository.save(contactIndus); }  

        if (demandeDto != null && demandeDto.getIdExperimentation() != null) {
            ajouterMissionAUtilisateur(savedUser.getIdUtilisateur(), demandeDto.getIdExperimentation(), demandeDto.getRolePourCetteExpe());
        }
        return savedUser;
    }

    @Transactional
    public Professionnel ajouterProfilPro(Long idUtilisateur, Professionnel pro) {
        Utilisateur user = utilisateurRepository.findById(idUtilisateur)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        pro.setUtilisateur(user);
        user.setProfilPro(pro); 
        
        return professionnelRepository.save(pro);
    }

    @Transactional
    public NonProfessionnel ajouterProfilNonPro(Long idUtilisateur, NonProfessionnel nonPro) {
        Utilisateur user = utilisateurRepository.findById(idUtilisateur)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        nonPro.setUtilisateur(user);
        user.setProfilNonPro(nonPro);
        
        return nonProfessionnelRepository.save(nonPro);
    }

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


    public Optional<Utilisateur> verifierExistence(String nom, String prenom, Date date) {
        return utilisateurRepository.findByNomAndPrenomAndDateNaissance(nom, prenom, date);
    }

    public List<Utilisateur> listerTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

}