package Formulaire.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "professionnel")
@Data
@NoArgsConstructor
public class Professionnel {
    
    @Id
    private Long idUtilisateur; // Prendra automatiquement l'ID de l'utilisateur

    @OneToOne
    @MapsId // Indique que la clé primaire est la même que celle de la relation
    @JoinColumn(name = "id_utilisateur", nullable = false)
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

    @OneToMany(mappedBy = "professionnel", cascade = CascadeType.ALL)
    private ArrayList<NonProfessionnel> contactsNonPro = new ArrayList<>();

}