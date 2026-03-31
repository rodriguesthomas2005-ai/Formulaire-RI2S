package Formulaire.service;

import Formulaire.entity.DossierCandidature;
import Formulaire.entity.Fichier;
import Formulaire.entity.Industriel;
import Formulaire.repository.IndustrielRepository; 
import Formulaire.repository.DossierCandidatureRepository; 
import Formulaire.repository.FichierRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class IndustrielService {

    @Autowired private IndustrielRepository industrielRepository;
    @Autowired private DossierCandidatureRepository dossierRepository;
    @Autowired private FichierRepository fichierRepository;

    @Transactional
    public Industriel inscrireIndustriel(Industriel industriel, DossierCandidature dossier, Fichier fichier, Long idUtilisateur) {
        
        // 1. On lie l'industriel à l'ID de l'utilisateur (PersonneContact)
        industriel.setIdUtilisateur(idUtilisateur);
        Industriel savedIndustriel = industrielRepository.save(industriel);

        // 2. On enregistre le dossier s'il existe
        if (dossier != null) {
            // Ici, il faudrait idéalement un lien dans DossierCandidature vers l'Industriel
            dossierRepository.save(dossier);
        }

        // 3. On enregistre le fichier s'il existe
        if (fichier != null) {
            fichierRepository.save(fichier);
        }

        return savedIndustriel;
    }
}