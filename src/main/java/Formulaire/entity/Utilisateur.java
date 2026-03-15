package Formulaire.entity;

import jakarta.persistence.*;
import lombok.*;

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


    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private Professionnel profilPro;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private NonProfessionnel profilNonPro;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private java.util.List<DemandeInscriptionExpe> demandesExperimentations;
}