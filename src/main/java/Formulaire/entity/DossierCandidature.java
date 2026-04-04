package Formulaire.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    private String nomSolution;

    @NonNull
    @Column(length = 1000)
    private String descriptionSolution;

    @NonNull
    @Column(length = 1000)
    private String problematique;

    @NonNull
    @Column(length = 1000)
    private String typrInnovation;

    @NonNull
    @Column(length = 1000)
    private String benefices;

    @NonNull
    @Column(length = 1000)
    private String caractereInnovant;

    @NonNull
    @Column(length = 1000)
    private String coconception;

    @NonNull
    @Column(length = 1000)
    private String implication;

    @NonNull
    @Column(length = 1000)
    private Boolean comite;

    @NonNull
    @Column(length = 1000)
    private String marche;

    @NonNull
    @Column(length = 1000)
    private String modeleEco;

    @NonNull
    @Column(length = 1000)
    private String commercialisation;

    @NonNull
    @Column(length = 1000)
    private String financement;

    @NonNull
    @Column(length = 1000)
    private String concurrents;

    @NonNull
    @Column(length = 1000)
    private String equipe;

    @NonNull
    @Column(length = 1000)
    private String accompagnement;

    @NonNull
    private Boolean tiersLieux;

    @NonNull
    @Column(length = 1000)
    private String pourquoiRi2s;

    @NonNull
    @Column(length = 1000)
    private Integer trl;

    @NonNull
    @Column(length = 1000)
    private String justificationTrl;

    @NonNull
    private Boolean dispositifMedical;

    @NonNull
    @Column(length = 1000)
    private String justificationDispositifMedical;

    @Column(length = 1000)
    private String classeDispositif;

    @NonNull
    @Column(length = 1000)
    private String besoins;

    @NonNull
    @Column(length = 1000)
    private String descriptionBesoins;

    @NonNull
    @Column(length = 1000)
    private String questiosProjet;

    @NonNull
    @Column(length = 1000)
    private String terrainExperimentation;

    @NonNull
    @Column(length = 1000)
    private String conclusion;

    @ManyToOne
    @JoinColumn(name = "id_industriel")
    @JsonBackReference
    private Industriel industriel;

    @OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Fichier> fichiers = new ArrayList<>();
}
