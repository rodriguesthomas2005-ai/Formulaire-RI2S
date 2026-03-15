package Formulaire.entity;

import java.time.LocalDateTime;
import Formulaire.entity.Role;
import jakarta.persistence.*;
import lombok.*;

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
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_experimentation", nullable = false)
    private Experimentation experimentation;

    private LocalDateTime dateDemande;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    // --- LE RÔLE EN ENUM ---
    @Enumerated(EnumType.STRING)
    private Role rolePourCetteExpe; 
}