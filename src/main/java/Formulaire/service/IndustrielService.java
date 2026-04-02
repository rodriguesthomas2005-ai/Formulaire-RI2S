package Formulaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Formulaire.entity.DossierCandidature;
import Formulaire.entity.Industriel;
import Formulaire.entity.PersonneContactIndustriel;
import Formulaire.repository.DossierCandidatureRepository;
import Formulaire.repository.FichierRepository;
import Formulaire.repository.IndustrielRepository;
import Formulaire.repository.PersonneContactIndustrielRepository;
import Formulaire.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
@Service
public class IndustrielService {

    @Autowired private IndustrielRepository industrielRepository;
    @Autowired private DossierCandidatureRepository dossierRepository;
    @Autowired private FichierRepository fichierRepository;
    @Autowired private UtilisateurRepository utilisateurRepository;
    @Autowired private PersonneContactIndustrielRepository personneContactRepository;

    @Transactional
    public Industriel inscrireIndustriel(Industriel industriel, DossierCandidature dossier, Long idUser) {
        // 1. Trouver le profil de contact existant
        PersonneContactIndustriel contact = personneContactRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Cet utilisateur n'est pas déclaré comme contact industriel"));
        
        // 2. Relier l'industriel au contact
        industriel.setPersonneContact(contact);

        // 3. Relier le dossier à l'industriel
        if (dossier != null) {
            dossier.setIndustriel(industriel);
            industriel.getDossiers().add(dossier);
            
            // NOTE : On ne traite plus "fichier" ici car le Controller 
            // a déjà rempli la liste dossier.getFichiers()
        }

        // 4. Sauvegarder l'industriel (la cascade fera le reste pour le dossier et les fichiers)
        return industrielRepository.save(industriel);
    }

    @Transactional
    public List<Industriel> listerTout() {
    return industrielRepository.findAll();
    }


    @Transactional
    public Industriel trouverParId(Long id) {
        return industrielRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Industriel non trouvé"));
    }
}