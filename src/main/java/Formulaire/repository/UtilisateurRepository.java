package Formulaire.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Formulaire.entity.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByNomIgnoreCaseAndPrenomIgnoreCaseAndDateNaissance(
        String nom, String prenom, Date dateNaissance
    );
}