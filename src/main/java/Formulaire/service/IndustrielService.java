package Formulaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Formulaire.entity.DossierCandidature;
import Formulaire.entity.Fichier;
import Formulaire.entity.Industriel;
import Formulaire.entity.Utilisateur;
import Formulaire.repository.DossierCandidatureRepository;
import Formulaire.repository.FichierRepository;
import Formulaire.repository.IndustrielRepository;
import jakarta.transaction.Transactional;
import Formulaire.repository.UtilisateurRepository;
@Service
public class IndustrielService {

    @Autowired private IndustrielRepository industrielRepository;
    @Autowired private DossierCandidatureRepository dossierRepository;
    @Autowired private FichierRepository fichierRepository;
    @Autowired private UtilisateurRepository utilisateurRepository;

    @Transactional
    public Industriel inscrireIndustriel(Industriel industriel, DossierCandidature dossier, Fichier fichier, Long idUser) {
        Utilisateur user = utilisateurRepository.findById(idUser)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        
        industriel.setIdUtilisateur(idUser);

        if (dossier != null) {
            dossier.setIndustriel(industriel);
            industriel.getDossiers().add(dossier); 
            
            if (fichier != null) {
                fichier.setDossier(dossier);
                dossier.getFichiers().add(fichier);
            }
        }

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