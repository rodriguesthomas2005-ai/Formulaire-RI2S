
package Formulaire.repository;

import Formulaire.entity.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichierRepository extends JpaRepository<Fichier, Long> {
    // Spring Boot génère automatiquement toutes les méthodes SQL
}