package Formulaire.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParticipation;

    @ManyToOne
    @JoinColumn(name = "id_dossier")
    @JsonBackReference
    private DossierInscriptionExpe dossier;

    @ManyToOne
    @JoinColumn(name = "id_non_pro")
    private NonProfessionnel nonProfessionnel;

    private String roleJoue;

}