package Formulaire.entity;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "utilisateur")
@Data
@NoArgsConstructor
public class Utilisateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;

    @NonNull
    @Column(nullable = false)
    private String nom;

    @NonNull
    @Column(nullable = false)
    private String prenom;

    @NonNull
    @Column(nullable = false)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String telephone;

    @NonNull
    @Column(nullable = false)
    private Boolean consentement;

    @NonNull
    @Column(nullable = false)
    private Date dateNaissance;

    @NonNull
    @Column(nullable = false)
    private Integer codePostal;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Professionnel profilPro;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private NonProfessionnel profilNonPro;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // <--- Ajoute fetch = FetchType.EAGER
    @JsonManagedReference
    private List<DemandeInscriptionExpe> demandesExperimentations;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private PersonneContactIndustriel profilIndustriel;
}