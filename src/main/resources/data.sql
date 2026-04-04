INSERT INTO utilisateur (id_utilisateur, nom, prenom, consentement, date_naissance, code_postal) VALUES 
(1, 'Boulanger', 'Yvette', true, '1945-05-12', 81000),
(2, 'Boulanger', 'Marc', true, '1975-10-20', 81100),
(3, 'Lemaire', 'Sophie', true, '1982-03-15', 81200),
(4, 'Ravi', 'Clémentine', true, '2000-01-01', 81100);

-- 3. LES EXPERIMENTATIONS
INSERT INTO experimentation (id_experimentation, nom_experimentation, necessite_aidant, necessite_pro, description, date_debut_expe, date_fin_expe, url_image) VALUES 
(1, 'Presage', true, false, 'Outils d’IA pour prédire les risques d’hospitalisation.', '2026-06-01 10:00:00', '2026-06-30 10:00:00', 'https://presage.care/logo.png'),
(2, 'Telegragik', true, false, 'Détection de perte d’autonomie via capteurs.', '2026-06-01 10:00:00', '2026-06-30 10:00:00', 'https://telegrafik.fr/logo.svg'),
(3, 'ExpeAvecProEtAidant', true, true, 'Projet complet avec suivi pro et aidant.', '2026-06-01 10:00:00', '2026-06-30 10:00:00', 'https://img.png'),
(4, 'ExpeAvecProSansAidant', false, true, 'Projet pour professionnels uniquement.', '2026-06-01 10:00:00', '2026-06-30 10:00:00', 'https://img.png');

-- 4. PROFILS NON-PRO (Yvette et Marc)
INSERT INTO non_professionnel (id_utilisateur, participation_expe, email_non_pro, telephone_non_pro) VALUES 
(1, 'OUI', 'yvette.boulanger@gmail.com', '0563112233'),
(2, 'OUI', 'marc.boulanger@gmail.com', '0612345678');

INSERT INTO utilisateur_moments (id_utilisateur, moment) VALUES 
(1, 'MATIN'), (1, 'SOIR'), (2, 'APRES_MIDI');

-- 5. PROFIL PROFESSIONNEL (Sophie)
INSERT INTO professionnel (id_utilisateur, nom_fonction, structure, participation_expe, profession, ville_etablissement, zone_geo_patient, milieu_professionnel, info_complementaires, connaissanceri2s, email_pro, telephone_pro) 
VALUES (3, 'Médecin Généraliste', 'Cabinet Médical Castres', 'OUI', 'Médecin', 'Castres', 'Tarn Sud', 'Libéral', 'Disponible jeudi', 'Bouche à oreille', 'sophie.lemaire@sante.fr', '0563445566');

-- 6. LE DOSSIER DE GROUPE (Indispensable pour lier Senior et Aidant)
INSERT INTO dossier_inscription_expe (id_dossier, id_expe, id_professionnel, date_creation) 
VALUES (1, 1, null, CURRENT_TIMESTAMP);

-- 7. LES INSCRIPTIONS (Liées au dossier 1)
INSERT INTO demande_inscription_expe (id_utilisateur, id_experimentation, id_dossier, role_pour_cette_expe, statut, date_demande) VALUES 
(1, 1, 1, 'SENIOR', 'EN_ATTENTE', CURRENT_TIMESTAMP),
(2, 1, 1, 'AIDANT', 'ACCEPTEE', CURRENT_TIMESTAMP);

-- 8. INDUSTRIEL & CONTACT
INSERT INTO personne_contact_industriel (id_utilisateur, fonction, email_pers_contact, telephone_pers_contact) 
VALUES (4, 'Directrice Innovation', 'clementine.ravi@medtech-vision.fr', '0612345678');

INSERT INTO industriel (id_personne_contact, nom_entreprise, siret, adresse_industriel, date_creation, effectif, structure_juridique, site_web, autre_lien) 
VALUES (4, 'MedTech Vision', 88877766600011, '123 Rue Tech, Toulouse', '2024-02-15', 8, 'SAS', 'www.medtech.fr', 'linkedin.com/medtech');

-- 9. DOSSIER CANDIDATURE (Respect des fautes de frappe Java)
INSERT INTO dossier_candidature (id_candidature, id_industriel, statue_dossier, nom_dossier, nom_solution, description_solution, problematique, typr_innovation, benefices, caractere_innovant, coconception, implication, comite, marche, modele_eco, commercialisation, financement, concurrents, equipe, accompagnement, tiers_lieux, pourquoi_ri2s, trl, justification_trl, dispositif_medical, justification_dispositif_medical, besoins, description_besoins, questios_projet, terrain_experimentation, conclusion) 
VALUES (1, 4, 'EN_ATTENTE', 'Dossier V1', 'Solution IA', 'Algorithme prédiction', 'Manque suivi', 'LOGICIEL', 'Réduction hospit', 'Unique', 'OUI', 'FORTE', true, 'France', 'Abonnement', 'B2B', 'Fonds', 'Concur X', '2 pers', 'Incubateur', false, 'Expertise', 6, 'Testé', true, 'En cours', 'Accès patients', '50 testeurs', 'Flux ?', 'EHPAD', 'Prêt');

-- 10. FICHIER
INSERT INTO fichier (id_dossier, nom_fichier, type, taille, donnees) 
VALUES (1, 'schema.pdf', 'application/pdf', '2.4 MB', '\x25504446');

-- 11. REGLAGE DES SEQUENCES (PostgreSQL)
SELECT setval('utilisateur_id_utilisateur_seq', (SELECT MAX(id_utilisateur) FROM utilisateur));
SELECT setval('experimentation_id_experimentation_seq', (SELECT MAX(id_experimentation) FROM experimentation));
SELECT setval('dossier_inscription_expe_id_dossier_seq', (SELECT MAX(id_dossier) FROM dossier_inscription_expe));
SELECT setval('demande_inscription_expe_id_demande_seq', (SELECT MAX(id_demande) FROM demande_inscription_expe));