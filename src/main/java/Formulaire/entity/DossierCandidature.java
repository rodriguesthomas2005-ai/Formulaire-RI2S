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
    private String descriptionSolution;

    @NonNull
    @Column(length = 1000)
    private String equipeEcosysteme;

    @NonNull
    @Column(length = 1000)
    private String integrationMarche;

    @NonNull
    @Column(length = 1000)
    private String technologieNiveauMaturite;

    @NonNull
    @Column(length = 1000)
    private String projetExpeSouhaite;

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
