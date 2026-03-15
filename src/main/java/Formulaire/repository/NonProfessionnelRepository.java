package Formulaire.repository;

import Formulaire.entity.NonProfessionnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonProfessionnelRepository extends JpaRepository<NonProfessionnel, Long> {
    
}