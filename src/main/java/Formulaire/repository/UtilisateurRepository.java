package Formulaire.repository;

import Formulaire.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    
    // Spring Boot va générer le SQL tout seul (SELECT * FROM utilisateur WHERE email = ?)
    Optional<Utilisateur> findByEmail(String email);
}