package Formulaire.repository;

import Formulaire.entity.DemandeInscriptionExpe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeInscriptionExpeRepository extends JpaRepository<DemandeInscriptionExpe, Long> {
    // Spring Boot génère automatiquement toutes les méthodes SQL
}
