-- 1. NETTOYAGE (Ordre strict pour les contraintes)
TRUNCATE TABLE fichier, dossier_candidature, industriel, personne_contact_industriel, 
               demande_inscription_expe, utilisateur_moments, professionnel, 
               non_professionnel, utilisateur, experimentation CASCADE;

-- 2. LES UTILISATEURS
INSERT INTO utilisateur (id_utilisateur, nom, prenom, consentement, date_naissance, code_postal) VALUES 
(1, 'Boulanger', 'Yvette', true, '1945-05-12', 81000), -- La Sénior
(2, 'Boulanger', 'Marc', true, '1975-10-20', 81100),   -- L'Aidant
(3, 'Lemaire', 'Sophie', true, '1982-03-15', 81200);   -- La Pro / Contact Indus

-- 3. LES EXPERIMENTATIONS
INSERT INTO experimentation (id_experimentation, nom_experimentation, necessite_aidant, necessite_pro, description) 
VALUES (1, 'Presage', true, false, 'Détection de fragilité chez les séniors');

-- 4. PROFILS NON-PROFESSIONNELS
INSERT INTO non_professionnel (id_utilisateur, participation_expe, email_non_pro, telephone_non_pro) VALUES 
(1, 'OUI', 'yvette.b@orange.fr', '0563112233'),
(2, 'OUI', 'marc.b@gmail.com', '0612345678');

INSERT INTO utilisateur_moments (id_utilisateur, moment) VALUES (1, 'MATIN'), (1, 'SOIR'), (2, 'APRES_MIDI');

-- 5. PROFIL PROFESSIONNEL (Sophie)
INSERT INTO professionnel (id_utilisateur, nom_fonction, structure, participation_expe, profession, ville_etablissement, zone_geo_patient, milieu_professionnel, email_pro, telephone_pro) 
VALUES (3, 'Médecin Généraliste', 'Cabinet Médical', 'OUI', 'Médecin', 'Castres', 'Tarn', 'Libéral', 'dr.sophie@sante.fr', '0563445566');

-- 6. PARTIE INDUSTRIEL (Sophie est aussi contact pour son entreprise)
INSERT INTO personne_contact_industriel (id_utilisateur, fonction) VALUES (3, 'Fondatrice MedTech');

INSERT INTO industriel (id_utilisateur, nom_entreprise, siret, mail_industriel, date_creation, effectif, structure_juridique, site_web) 
VALUES (3, 'SanteConnect', 88877766600011, 'contact@santeconnect.fr', '2024-01-01', 10, 'SAS', 'www.santeconnect.fr');

-- 7. DOSSIER ET FICHIER
INSERT INTO dossier_candidature (id_candidature, id_industriel, nom_dossier, statue_dossier, description_solution) 
VALUES (1, 3, 'Projet IA Diabète', 'EN_ATTENTE', 'Algorithme de suivi glycémique');

INSERT INTO fichier (id_fichier, id_dossier, nom_fichier, url, type, statut_scan) 
VALUES (1, 1, 'presentation.pdf', 'https://cloud.com/doc.pdf', 'PDF', 'CLEAN');

-- 8. INSCRIPTIONS AUX MISSIONS
INSERT INTO demande_inscription_expe (id_utilisateur, id_experimentation, role_pour_cette_expe, statut, date_demande) VALUES 
(1, 1, 'SENIOR', 'EN_ATTENTE', CURRENT_TIMESTAMP),
(2, 1, 'AIDANT', 'ACCEPTEE', CURRENT_TIMESTAMP);

-- 9. REGLAGE SEQUENCE
ALTER TABLE utilisateur ALTER COLUMN id_utilisateur RESTART WITH 4;
ALTER TABLE dossier_candidature ALTER COLUMN id_candidature RESTART WITH 2;
ALTER TABLE fichier ALTER COLUMN id_fichier RESTART WITH 2;