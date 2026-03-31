package Formulaire.repository;

import Formulaire.entity.DossierCandidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierCandidatureRepository extends JpaRepository<DossierCandidature, Long> {
    // Spring Boot génère automatiquement toutes les méthodes SQL
}