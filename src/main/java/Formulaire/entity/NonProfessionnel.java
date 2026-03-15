package Formulaire.entity;

import Formulaire.entity.MomentJournee;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

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
    private Utilisateur utilisateur;
    
    @NonNull
    @Column(nullable = false)
    private Date dateNaissance;

    @Enumerated(EnumType.STRING) 
    @Column(nullable = false)
    private MomentJournee momentJournee;

    @NonNull
    @Column(nullable = false)
    private String participationExpe;

    @ManyToOne
    @JoinColumn(name = "professionnel_id")
    private Professionnel professionnel;
}