package Formulaire.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "PersonneContactIndustriel")
@Data
@NoArgsConstructor

public class PersonneContactIndustriel {
    @Id
    private Long idUtilisateur;

    @NonNull
    @Column(nullable = false)
    private String fonction;
}
