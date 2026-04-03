package Formulaire.DTO;

import java.util.List;

import Formulaire.entity.DossierCandidature;
import Formulaire.entity.Fichier;
import Formulaire.entity.Industriel;
import Formulaire.entity.NonProfessionnel;
import Formulaire.entity.PersonneContactIndustriel;
import Formulaire.entity.Professionnel;
import Formulaire.entity.Role;
import Formulaire.entity.Utilisateur;
import lombok.Data;

@Data
public class InscriptionRequest {
    
    private Utilisateur utilisateur;
    private Professionnel profilPro;
    private NonProfessionnel profilNonPro;
    private PersonneContactIndustriel personneContactIndustriel;
    private DemandeExpeDTO demandeExpe; 
    private Industriel industriel;
    private DossierCandidature dossierCandidature;
    private Fichier fichier;

    @Data
    public static class DemandeExpeDTO {
        private Long idExperimentation;
        private Role rolePourCetteExpe; 
    }

    @Data
    public static class UpgradeNonProDTO {
    private String participationExpe;
    private List<String> momentsJournee;
    private String emailNonPro;   
    private String telephoneNonPro;
    }


    @Data
    public static class UpgradeProDTO {
        private String emailPro;
        private String telephonePro;
        private String nomFonction;
        private String structure;
        private String participationExpe;
        private String profession;
        private String villeEtablissement;
        private String zoneGeoPatient;
        private String milieuProfessionnel;
        private String connaissanceRI2S;
        private String infoComplementaires;
    }

    @Data
    public static class ContactIndustrielDTO {
        private String fonction;
        private String emailPersContact;
        private String telephonePersContact;
    }
}
    