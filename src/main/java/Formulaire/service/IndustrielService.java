package Formulaire.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Formulaire.entity.DossierCandidature;
import Formulaire.entity.Fichier;
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
public Industriel inscrireIndustriel(Industriel industriel, DossierCandidature dossier, Fichier fichier, Long idUser) {
    // 1. Trouver le profil de contact existant pour cet utilisateur
    PersonneContactIndustriel contact = personneContactRepository.findById(idUser)
            .orElseThrow(() -> new RuntimeException("Cet utilisateur n'est pas déclaré comme contact industriel"));
    
    // 2. Relier l'industriel au contact
    industriel.setPersonneContact(contact);

    if (dossier != null) {
        dossier.setIndustriel(industriel);
        industriel.getDossiers().add(dossier);
    }
    if (fichier != null) {
    fichier.setDossier(dossier);
    dossier.getFichiers().add(fichier); // <--- CETTE LIGNE est nécessaire pour le retour JSON
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

    @Transactional
    public List<Fichier> ajouterFichiers(Long idDossier, List<MultipartFile> files) throws Exception {
        // 1. Récupérer le dossier
        DossierCandidature dossier = dossierRepository.findById(idDossier)
                .orElseThrow(() -> new RuntimeException("Dossier non trouvé"));

        List<Fichier> fichiersEnregistres = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Fichier f = new Fichier();
                f.setNomFichier(file.getOriginalFilename());
                f.setDonnees(file.getBytes());
                f.setType(file.getContentType());
                f.setTaille((file.getSize() / 1024) + " KB");
                f.setDossier(dossier); // Liaison @ManyToOne
                
                // On l'ajoute à la liste du dossier pour la cohérence JPA
                dossier.getFichiers().add(f);
                
                // Sauvegarde individuelle (ou via cascade sur le dossier)
                fichiersEnregistres.add(fichierRepository.save(f));
            }
        }
        
        return fichiersEnregistres;
    }
}