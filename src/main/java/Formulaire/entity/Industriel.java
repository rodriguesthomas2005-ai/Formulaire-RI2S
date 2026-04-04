package Formulaire.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity
@Table(name = "Industriel")
@Data
@NoArgsConstructor

public class Industriel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIndustriel;

    @OneToOne
    @JoinColumn(name = "id_personne_contact") 
    private PersonneContactIndustriel personneContact;

    @NonNull
    @Column(nullable = false)
    private String nomEntreprise;

    @NonNull
    @Column(nullable = false)
    private Long siret;

    @NonNull
    @Column(nullable = false)
    private String adresseIndustriel;

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

    private Boolean transfere =false; 

    @OneToMany(mappedBy = "industriel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<DossierCandidature> dossiers = new ArrayList<>();
    
}
