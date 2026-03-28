package Formulaire.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "professionnel")
@Data
@NoArgsConstructor
public class Professionnel {
    
    @Id
    private Long idUtilisateur;

    @OneToOne
    @MapsId 
    @JoinColumn(name = "id_utilisateur", nullable = false)
    @JsonBackReference
    private Utilisateur utilisateur;
    
    @NonNull
    @Column(nullable = false)
    private String nomFonction;

    @NonNull
    @Column(nullable = false)
    private String structure;

    @NonNull
    @Column(nullable = false)
    private String participationExpe;

    @NonNull
    @Column(nullable = false)
    private String profession;
    
    @NonNull
    @Column(nullable = false)
    private String villeEtablissement;

    @NonNull
    @Column(nullable = false)
    private String zoneGeoPatient;

    @NonNull
    @Column(nullable = false)
    private String milieuProfessionnel;

    private String connaissanceRI2S;

    @Column(length = 1000)
    private String infoComplementaires;
}