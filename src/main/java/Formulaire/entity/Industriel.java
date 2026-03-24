package Formulaire.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.Date;

@Entity
@Table(name = "Industriel")
@Data
@NoArgsConstructor

public class Industriel {
    @Id
    private Long idUtilisateur;

    @NonNull
    @Column(nullable = false)
    private String nomEntreprise;

    @NonNull
    @Column(nullable = false)
    private int SIRET;

    @NonNull
    @Column(nullable = false)
    private String mailIndustriel;

    @NonNull
    @Column(nullable = false)
    private Date dateCreation;

    @NonNull
    @Column(nullable = false)
    private int Effectif;

    @NonNull
    @Column(nullable = false)
    private String structureJuridique;

    @NonNull
    @Column(nullable = false)
    private String siteWeb;

    @NonNull
    @Column(nullable = false)
    private String autreLien;
}
