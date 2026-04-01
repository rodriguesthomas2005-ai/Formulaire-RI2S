-- 1. NETTOYAGE DES TABLES (Ordre important pour les clés étrangères)
TRUNCATE TABLE demande_inscription_expe CASCADE;
TRUNCATE TABLE professionnel CASCADE;
TRUNCATE TABLE non_professionnel CASCADE;
TRUNCATE TABLE personne_contact_industriel CASCADE;
TRUNCATE TABLE utilisateur CASCADE;
TRUNCATE TABLE experimentation CASCADE;

-- 2. LES UTILISATEURS
INSERT INTO utilisateur (id_utilisateur, nom, prenom, telephone, consentement, date_naissance, code_postal) 
VALUES (1, 'Boulanger', 'Yvette', '0563112233', true, '1945-05-12', 81000);

INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance, code_postal) 
VALUES (2, 'Boulanger', 'Marc', 'marc.boulanger@gmail.com', '0612345678', true, '1975-10-20', 81100);

INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance, code_postal) 
VALUES (3, 'Lemaire', 'Sophie', 'dr.lemaire@sante.fr', '0563445566', true, '1982-03-15', 81200);

-- 3. LES EXPERIMENTATIONS
INSERT INTO experimentation (id_experimentation, nom_experimentation, necessite_aidant, necessite_pro, description, date_debut_expe, date_fin_expe) 
VALUES (1, 'Presage',  true, false, 'Test de détection de fragilité', '2026-06-01 10:00:00', '2026-06-30 10:00:00');

-- 4. PROFIL NON-PROFESSIONNEL (Yvette et Marc)
INSERT INTO non_professionnel (id_utilisateur, participation_expe, email_non_pro, telephone_non_pro) VALUES (1, 'OUI', 'yvette.boulanger@gmail.com', '0563112233');
INSERT INTO non_professionnel (id_utilisateur, participation_expe, email_non_pro, telephone_non_pro) VALUES (2, 'OUI', 'marc.boulanger@gmail.com', '0612345678');

INSERT INTO utilisateur_moments (id_utilisateur, moment) VALUES (1, 'MATIN');
INSERT INTO utilisateur_moments (id_utilisateur, moment) VALUES (1, 'SOIR');
INSERT INTO utilisateur_moments (id_utilisateur, moment) VALUES (2, 'APRES_MIDI');

-- 5. PROFIL PROFESSIONNEL (Sophie) - AVEC TES NOUVEAUX CHAMPS
INSERT INTO professionnel (
    id_utilisateur, 
    nom_fonction, 
    structure, 
    participation_expe, 
    profession, 
    ville_etablissement, 
    zone_geo_patient, 
    milieu_professionnel, 
    info_complementaires, 
    connaissanceri2s, 
    email_pro, 
    telephone_pro
) VALUES (
    3, 
    'Médecin Généraliste', 
    'Cabinet Médical Castres', 
    'OUI', 
    'Médecin', 
    'Castres', 
    'Tarn Sud', 
    'Libéral', 
    'Disponible le jeudi après-midi pour les suivis.', 
    'Bouche à oreille',
    'sophie.lemaire@sante.fr',
    '0563445566'
);

-- 6. LES INSCRIPTIONS AUX MISSIONS
INSERT INTO demande_inscription_expe (id_utilisateur, id_experimentation, role_pour_cette_expe, statut, date_demande) 
VALUES (1, 1, 'SENIOR', 'EN_ATTENTE', CURRENT_TIMESTAMP);

INSERT INTO demande_inscription_expe (id_utilisateur, id_experimentation, role_pour_cette_expe, statut, date_demande) 
VALUES (2, 1, 'AIDANT', 'ACCEPTEE', CURRENT_TIMESTAMP);

-- 7. REGLAGE SEQUENCE (Pour que les prochains IDs auto-générés ne créent pas de conflit)
ALTER TABLE utilisateur ALTER COLUMN id_utilisateur RESTART WITH 10;