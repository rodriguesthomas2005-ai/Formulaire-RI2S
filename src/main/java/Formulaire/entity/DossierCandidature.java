package Formulaire.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @ManyToOne
    @JoinColumn(name = "id_industriel")
    @JsonBackReference
    private Industriel industriel;

    @OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Fichier> fichiers = new ArrayList<>();
}
