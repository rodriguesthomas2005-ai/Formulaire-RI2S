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
        // 1. Récupérer l'utilisateur existant
        Utilisateur user = utilisateurRepository.findById(idUtilisateur)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + idUtilisateur));

        // 2. Créer l'entité Professionnel et mapper les champs
        Professionnel pro = new Professionnel();
        pro.setNomFonction(dto.getNomFonction());
        pro.setStructure(dto.getStructure());
        pro.setParticipationExpe(dto.getParticipationExpe());
        pro.setProfession(dto.getProfession());
        pro.setVilleEtablissement(dto.getVilleEtablissement());
        pro.setZoneGeoPatient(dto.getZoneGeoPatient());
        pro.setMilieuProfessionnel(dto.getMilieuProfessionnel());
        pro.setConnaissanceRI2S(dto.getConnaissanceRI2S());
        pro.setInfoComplementaires(dto.getInfoComplementaires());

        // 3. Lier l'utilisateur (Indispensable pour la clé primaire partagée @MapsId)
        pro.setUtilisateur(user);
        
        // 4. Sauvegarder
        return professionnelRepository.save(pro);
    }

    @Transactional
    public NonProfessionnel ajouterProfilNonPro(Long idUtilisateur, UpgradeNonProDTO dto) {
        // 1. On cherche l'utilisateur
        Utilisateur user = utilisateurRepository.findById(idUtilisateur)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // 2. On crée l'entité à partir du DTO
        NonProfessionnel nonPro = new NonProfessionnel();
        nonPro.setParticipationExpe(dto.getParticipationExpe());
        
        // Si momentsJournee est un Set d'Enums, conversion si nécessaire
        // nonPro.setMomentsJournee(dto.getMomentsJournee()); 

        // 3. On lie l'utilisateur (Crucial pour @MapsId)
        nonPro.setUtilisateur(user);
        
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