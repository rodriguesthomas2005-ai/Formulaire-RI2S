package Formulaire.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Formulaire.DTO.InscriptionRequest.DemandeExpeDTO;
import Formulaire.DTO.InscriptionRequest.DossierGroupageRequest;
import Formulaire.DTO.InscriptionRequest.UpgradeNonProDTO;
import Formulaire.DTO.InscriptionRequest.UpgradeProDTO;
import Formulaire.entity.DemandeInscriptionExpe;
import Formulaire.entity.DossierInscriptionExpe;
import Formulaire.entity.Experimentation;
import Formulaire.entity.NonProfessionnel;
import Formulaire.entity.PersonneContactIndustriel;
import Formulaire.entity.Professionnel;
import Formulaire.entity.Role;
import Formulaire.entity.Statut;
import Formulaire.entity.Utilisateur;
import Formulaire.repository.DemandeInscriptionExpeRepository;
import Formulaire.repository.DossierInscriptionExpeRepository;
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
    @Autowired private DemandeInscriptionExpeRepository demandeInscriptionExpeRepository;
    @Autowired private PersonneContactIndustrielRepository PersonneContactIndustrielRepository;
    @Autowired private DossierInscriptionExpeRepository dossierInscriptionExpeRepository;



   @Transactional
public Utilisateur inscrireUtilisateur(Utilisateur utilisateur, Professionnel pro, NonProfessionnel nonPro, DemandeExpeDTO demandeDto, PersonneContactIndustriel contactIndus) {

    Utilisateur savedUser = utilisateurRepository.save(utilisateur);

    if (pro != null) { pro.setUtilisateur(savedUser); professionnelRepository.save(pro); }
    if (nonPro != null) { nonPro.setUtilisateur(savedUser); nonProfessionnelRepository.save(nonPro); }
    if (contactIndus != null) { contactIndus.setUtilisateur(savedUser); PersonneContactIndustrielRepository.save(contactIndus); }  

    if (demandeDto != null && demandeDto.getIdExperimentation() != null) {
        // ✅ Ajout de "null" en 4ème argument car lors de l'inscription initiale, 
        // le dossier n'existe pas encore (il va être créé par le rôle SENIOR)
        ajouterMissionAUtilisateur(
            savedUser.getIdUtilisateur(), 
            demandeDto.getIdExperimentation(), 
            demandeDto.getRolePourCetteExpe(), 
            null
        );
    }
    return savedUser;
}
@Transactional
public DossierInscriptionExpe finaliserDossierGroupe(DossierGroupageRequest request) {
    // 1. Charger l'expérimentation
    Experimentation expe = experimentationRepository.findById(request.getIdExpe())
            .orElseThrow(() -> new RuntimeException("Expérimentation non trouvée"));

    // 2. Vérification des règles métier au moment du groupage
    if (Boolean.TRUE.equals(expe.getNecessiteAidant()) && request.getIdAidant() == null) {
        throw new RuntimeException("Erreur : Un aidant est obligatoire pour valider ce dossier.");
    }

    // 3. Créer le dossier unique pour ce binôme
    DossierInscriptionExpe dossier = new DossierInscriptionExpe();
    dossier.setExperimentation(expe);
    
    if (request.getIdPro() != null) {
        Professionnel pro = professionnelRepository.findById(request.getIdPro()).orElseThrow();
        dossier.setProfessionnelReferent(pro);
    }
    DossierInscriptionExpe savedDossier = dossierInscriptionExpeRepository.save(dossier);

    // 4. On "rattrape" les demandes indépendantes pour les lier au dossier
    lierDemandeAuDossier(request.getIdSenior(), request.getIdExpe(), savedDossier);
    
    if (request.getIdAidant() != null) {
        lierDemandeAuDossier(request.getIdAidant(), request.getIdExpe(), savedDossier);
    }

    return savedDossier;
}

private void lierDemandeAuDossier(Long idUser, Long idExpe, DossierInscriptionExpe dossier) {
    DemandeInscriptionExpe demande = demandeInscriptionExpeRepository
        .findByUtilisateurIdUtilisateurAndExperimentationIdExperimentation(idUser, idExpe)
        .orElseThrow(() -> new RuntimeException("Aucune demande d'inscription trouvée pour l'utilisateur " + idUser));
    
    demande.setDossier(dossier);
    demandeInscriptionExpeRepository.save(demande);
}

    private void rattacherDemande(Long idUser, Long idExpe, DossierInscriptionExpe dossier) {
        // On récupère la demande que l'utilisateur a créée lors de son inscription individuelle
        DemandeInscriptionExpe demande = demandeInscriptionExpeRepository
            .findByUtilisateurIdUtilisateurAndExperimentationIdExperimentation(idUser, idExpe)
            .orElseThrow(() -> new RuntimeException("Inscription individuelle introuvable pour l'ID: " + idUser));
        
        demande.setDossier(dossier);
        demandeInscriptionExpeRepository.save(demande);
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
    public PersonneContactIndustriel ajouterContactIndustriel(Long idUtilisateur, String fonction, String email, String tel) {
        Utilisateur user = utilisateurRepository.findById(idUtilisateur)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        PersonneContactIndustriel contact = new PersonneContactIndustriel();
        contact.setUtilisateur(user);
        contact.setFonction(fonction);
        contact.setEmailPersContact(email);
        contact.setTelephonePersContact(tel);

        return PersonneContactIndustrielRepository.save(contact);
    }

@Transactional
public DemandeInscriptionExpe ajouterMissionAUtilisateur(Long idUser, Long idExpe, Role role, Long idDossierExistant) {
    Utilisateur user = utilisateurRepository.findById(idUser).orElseThrow();
    Experimentation expe = experimentationRepository.findById(idExpe).orElseThrow();

    DemandeInscriptionExpe demande = new DemandeInscriptionExpe();
    demande.setUtilisateur(user);
    demande.setExperimentation(expe);
    demande.setRolePourCetteExpe(role);
    demande.setStatut(Statut.EN_ATTENTE);
    demande.setDateDemande(LocalDateTime.now());

    // On ne crée PAS de dossier ici, car on ne sait pas encore qui sera relié à qui.
    // Le dossier sera créé uniquement via la méthode 'finaliserDossierGroupe'.
    
    return demandeInscriptionExpeRepository.save(demande);
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