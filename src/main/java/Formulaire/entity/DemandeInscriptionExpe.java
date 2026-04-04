package Formulaire.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import jakarta.persistence.Transient;


@Entity
@Table(name = "demande_inscription_expe")
@Data
@NoArgsConstructor
public class DemandeInscriptionExpe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemande;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    @JsonBackReference 
    private Utilisateur utilisateur;

    @Transient // Ne pas créer de colonne en base de données
    @JsonProperty("idUtilisateur") // Sera affiché dans le JSON
    public Long getIdUtilisateurValue() {
        return utilisateur != null ? utilisateur.getIdUtilisateur() : null;
    }

    @ManyToOne
    @JoinColumn(name = "id_experimentation", nullable = false)
    private Experimentation experimentation;

    // Lien vers le groupe (Dossier)
    @ManyToOne
    @JoinColumn(name = "id_dossier")
    @JsonBackReference(value = "dossier-participants")
    private DossierInscriptionExpe dossier;

    private LocalDateTime dateDemande = LocalDateTime.now();
    private Boolean transfere = false; 

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.EN_ATTENTE;

    @Enumerated(EnumType.STRING)
    private Role rolePourCetteExpe;
}