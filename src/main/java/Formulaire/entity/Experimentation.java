package Formulaire.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "experimentation") // minuscule pour être raccord avec SQL
@Data
@NoArgsConstructor
public class Experimentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExperimentation;

    private String nomExperimentation;
    private Boolean necessiteAidant;
    private Boolean necessitePro;
    
    @Column(length = 1000)
    private String description;

    private Date dateDebutExpe;
    private Date dateFinExpe;

    // Simplifié en String pour éviter les erreurs de cast SQL VARBINARY
    private String criteresInclusion; 
}
