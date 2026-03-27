package Formulaire.DTO;

import Formulaire.entity.Role;
import Formulaire.entity.NonProfessionnel;
import Formulaire.entity.PersonneContactIndustriel;
import Formulaire.entity.Professionnel;
import Formulaire.entity.Utilisateur;
import lombok.Data;

@Data
public class InscriptionRequest {
    
    private Utilisateur utilisateur;
    private Professionnel profilPro;
    private NonProfessionnel profilNonPro;
    private PersonneContactIndustriel personneContactIndustriel;
    private DemandeExpeDTO demandeExpe; 

    @Data
    public static class DemandeExpeDTO {
        private Long idExperimentation;
        private Role rolePourCetteExpe; 
    }
}