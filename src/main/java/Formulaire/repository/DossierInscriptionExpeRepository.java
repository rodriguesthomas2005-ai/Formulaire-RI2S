
package Formulaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Formulaire.entity.DossierInscriptionExpe;

@Repository
public interface DossierInscriptionExpeRepository extends JpaRepository<DossierInscriptionExpe, Long> {
    // Spring Boot génère automatiquement toutes les méthodes SQL
}
