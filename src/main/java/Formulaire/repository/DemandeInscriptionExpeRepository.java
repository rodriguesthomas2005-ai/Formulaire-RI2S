package Formulaire.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Formulaire.entity.DemandeInscriptionExpe;

@Repository
public interface DemandeInscriptionExpeRepository extends JpaRepository<DemandeInscriptionExpe, Long> {
    // Spring Boot génère automatiquement toutes les méthodes SQL
    Optional<DemandeInscriptionExpe> findByUtilisateurIdUtilisateurAndExperimentationIdExperimentation(Long idUser, Long idExpe);
}
