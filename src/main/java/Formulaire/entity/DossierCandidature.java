package Formulaire.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.Date;

@Entity
@Table(name = "DossierCandidature")
@Data
@NoArgsConstructor

public class DossierCandidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidature;

    @NonNull
    @Column(length = 1000)
    private String statueDossier;

    @NonNull
    @Column(length = 1000)
    private String nom_dossier;

    @NonNull
    @Column(length = 1000)
    private String descriptionSolution;

    @NonNull
    @Column(length = 1000)
    private String equipeEcosysteme;

    @NonNull
    @Column(length = 1000)
    private String integrationMarche;

    @NonNull
    @Column(length = 1000)
    private String TechnologieNiveauMaturite;

    @NonNull
    @Column(length = 1000)
    private String ProjetExpeSouhaite;

    @NonNull
    @Column(length = 1000)
    private String commentaire;
}
