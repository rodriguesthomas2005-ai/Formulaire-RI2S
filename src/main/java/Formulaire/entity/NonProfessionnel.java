package Formulaire.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "non_professionnel")
@Data
@NoArgsConstructor
public class NonProfessionnel {
    
    @Id
    private Long idUtilisateur;

    @OneToOne
    @MapsId 
    @JoinColumn(name = "id_utilisateur", nullable = false)
    @JsonBackReference 
    private Utilisateur utilisateur;

    // @ElementCollection(targetClass = MomentJournee.class)
    // @CollectionTable(name = "utilisateur_moments", joinColumns = @JoinColumn(name = "id_utilisateur"))
    // @Enumerated(EnumType.STRING)
    // @Column(name = "moment")
    // private List<MomentJournee> momentsJournee;

    @NonNull
    @Column(nullable = false)
    private String participationExpe;

    @ManyToOne
    @JoinColumn(name = "professionnel_id")
    @JsonIgnore
    private Professionnel professionnel;
}