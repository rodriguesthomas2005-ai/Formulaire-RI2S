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
        // 1. Récupérer l'utilisateur (le contact)
        Utilisateur user = utilisateurRepository.findById(idUser)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        
        // 2. Lier l'industriel à l'utilisateur
        industriel.setIdUtilisateur(idUser);

        if (dossier != null) {
            // --- LA LIAISON MAGIQUE ICI ---
            dossier.setIndustriel(industriel); // On dit au dossier qui est son industriel
            industriel.getDossiers().add(dossier); // On ajoute le dossier à la liste de l'industriel
            
            if (fichier != null) {
                // --- LA LIAISON POUR LE FICHIER ---
                fichier.setDossier(dossier); // On dit au fichier qui est son dossier
                dossier.getFichiers().add(fichier); // On l'ajoute à la liste du dossier
            }
        }

        // Grâce au CascadeType.ALL dans tes entités, sauvegarder l'industriel
        // va automatiquement sauvegarder le dossier et le fichier liés.
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