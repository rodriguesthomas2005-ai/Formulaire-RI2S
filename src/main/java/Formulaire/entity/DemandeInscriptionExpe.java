package Formulaire.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

@Entity
@Table(name = "demande_inscription_expe")
@Data
@NoArgsConstructor
public class DemandeInscriptionExpe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemandeInscriptionExpe;

    @ManyToOne
@JoinColumn(name = "id_utilisateur", nullable = false)
@JsonBackReference 
private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_experimentation", nullable = false)
    private Experimentation experimentation;

    private LocalDateTime dateDemande;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    @Enumerated(EnumType.STRING)
    private Role rolePourCetteExpe; 
}