package Formulaire.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "dossier_inscription_expe")
@Data
@NoArgsConstructor
public class DossierInscriptionExpe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDossier;

    @ManyToOne
    @JoinColumn(name = "id_expe", nullable = false)
    private Experimentation experimentation;

    @ManyToOne
    @JoinColumn(name = "id_professionnel")
    private Professionnel professionnelReferent;

    @OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // <-- Ajoute fetch = FetchType.EAGER
@JsonManagedReference(value = "dossier-participants")
private List<DemandeInscriptionExpe> participants = new ArrayList<>();

    private LocalDateTime dateCreation = LocalDateTime.now();
}
