package Formulaire.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Formulaire.DTO.InscriptionRequest.DemandeExpeDTO;
import Formulaire.DTO.InscriptionRequest.UpgradeNonProDTO;
import Formulaire.DTO.InscriptionRequest.UpgradeProDTO;
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
    public Professionnel ajouterProfilPro(Long idUtilisateur, UpgradeProDTO dto) {
        Utilisateur user = utilisateurRepository.findById(idUtilisateur)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + idUtilisateur));
        Professionnel pro = new Professionnel();
        pro.setTelephonePro(dto.getTelephonePro()); 
        pro.setEmailPro(dto.getEmailPro());       
        pro.setNomFonction(dto.getNomFonction());
        pro.setStructure(dto.getStructure());
        pro.setParticipationExpe(dto.getParticipationExpe());
        pro.setProfession(dto.getProfession());
        pro.setVilleEtablissement(dto.getVilleEtablissement());
        pro.setZoneGeoPatient(dto.getZoneGeoPatient());
        pro.setMilieuProfessionnel(dto.getMilieuProfessionnel());
        pro.setConnaissanceRI2S(dto.getConnaissanceRI2S());
        pro.setInfoComplementaires(dto.getInfoComplementaires());
        pro.setUtilisateur(user);
        return professionnelRepository.save(pro);
    }

    @Transactional
    public NonProfessionnel ajouterProfilNonPro(Long idUtilisateur, UpgradeNonProDTO dto) {
        // 1. On cherche l'utilisateur
        Utilisateur user = utilisateurRepository.findById(idUtilisateur)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        NonProfessionnel nonPro = new NonProfessionnel();
        nonPro.setParticipationExpe(dto.getParticipationExpe());
        nonPro.setUtilisateur(user);
        nonPro.setEmailNonPro(dto.getEmailNonPro());
        nonPro.setTelephoneNonPro(dto.getTelephoneNonPro());
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


    public Optional<Utilisateur> verifierExistence(String nom, String prenom, Date dateNaissance) {
    return utilisateurRepository.findByNomIgnoreCaseAndPrenomIgnoreCaseAndDateNaissance(
        nom, prenom, dateNaissance
    );
}

    public List<Utilisateur> listerTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

}