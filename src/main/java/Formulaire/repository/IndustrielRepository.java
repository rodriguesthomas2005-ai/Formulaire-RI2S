
package Formulaire.repository;

import Formulaire.entity.Industriel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustrielRepository extends JpaRepository<Industriel, Long> {
    // Spring Boot génère automatiquement toutes les méthodes SQL
}