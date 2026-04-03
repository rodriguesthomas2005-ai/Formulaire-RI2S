package Formulaire.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "PersonneContactIndustriel")
@Data
@NoArgsConstructor

public class PersonneContactIndustriel {
    @Id
    private Long idUtilisateur;

    @NonNull
    @Column(nullable = false)
    private String fonction;

    @NonNull
    @Column(nullable = false)
    private String emailPersContact;

    @NonNull
    @Column(nullable = false)
    private String telephonePersContact;

    @OneToOne
    @MapsId 
    @JoinColumn(name = "id_utilisateur", nullable = false)
    @JsonBackReference
    private Utilisateur utilisateur;
}
