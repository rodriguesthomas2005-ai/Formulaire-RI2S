package Formulaire.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

    @OneToMany(mappedBy = "professionnel", cascade = CascadeType.ALL)
    private List<NonProfessionnel> contactsNonPro = new ArrayList<>();

}