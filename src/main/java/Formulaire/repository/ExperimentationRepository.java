package Formulaire.repository;

import Formulaire.entity.Experimentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentationRepository extends JpaRepository<Experimentation, Long> {

}