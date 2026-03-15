package Formulaire.repository;

import Formulaire.entity.Professionnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionnelRepository extends JpaRepository<Professionnel, Long> {

}