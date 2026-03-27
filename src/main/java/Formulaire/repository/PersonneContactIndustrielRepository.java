package Formulaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Formulaire.entity.PersonneContactIndustriel;

@Repository
public interface PersonneContactIndustrielRepository extends JpaRepository<PersonneContactIndustriel, Long> {
    
}