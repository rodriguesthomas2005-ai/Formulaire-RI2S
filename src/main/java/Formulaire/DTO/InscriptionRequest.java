package Formulaire.DTO;

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
}