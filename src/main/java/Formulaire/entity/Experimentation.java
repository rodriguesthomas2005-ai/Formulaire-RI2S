package Formulaire.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "experimentation") 
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
    private String urlImage;

    @ElementCollection
    @CollectionTable(name = "experimentation_criteres", joinColumns = @JoinColumn(name = "id_experimentation"))
    @Column(name = "critere")
    @OneToMany(mappedBy = "experimentation", fetch = FetchType.EAGER)
    private List<String> criteresInclusion = new ArrayList<>(); 
}
