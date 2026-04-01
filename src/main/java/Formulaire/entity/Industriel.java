package Formulaire.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "Industriel")
@Data
@NoArgsConstructor

public class Industriel {
    @Id
    private Long idUtilisateur;

    @NonNull
    @Column(nullable = false)
    private String nomEntreprise;

    @NonNull
    @Column(nullable = false)
    private Long siret;

    @NonNull
    @Column(nullable = false)
    private String mailIndustriel;

    @NonNull
    @Column(nullable = false)
    private Date dateCreation;

    @NonNull
    @Column(nullable = false)
    private Integer effectif;

    @NonNull
    @Column(nullable = false)
    private String structureJuridique;

    @NonNull
    @Column(nullable = false)
    private String siteWeb;

    @NonNull
    @Column(nullable = false)
    private String autreLien;

    @OneToMany(mappedBy = "industriel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<DossierCandidature> dossiers = new ArrayList<>();
    
}
