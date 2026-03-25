-- 1. UTILISATEURS
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (1, 'Boulanger', 'Yvette', 'yvette.b81@orange.fr', '0563112233', true, '1945-05-12');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (2, 'Boulanger', 'Marc', 'marc.boulanger@gmail.com', '0612345678', true, '1975-10-20');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (3, 'Lemaire', 'Sophie', 'dr.lemaire@sante.fr', '0563445566', true, '1982-03-15');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (4, 'Dupont', 'Robert', 'robert.dupont@wanadoo.fr', '0563778899', true, '1938-11-22');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (5, 'Moreau', 'Jean', 'j.moreau81@gmail.com', '0600112233', true, '1950-02-14');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (6, 'Durand', 'Marie', 'm.durand@outlook.fr', '0622334455', true, '1980-07-05');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (7, 'Giraud', 'Claire', 'claire.kine@ehpad.fr', '0788990011', true, '1990-12-01');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (8, 'Martin', 'Paul', 'paul.infirmier@liberal.fr', '0644556677', true, '1985-06-25');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (9, 'Lefebvre', 'Thomas', 't.lefebvre@medtech.fr', '0611223344', true, '1985-04-12');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (10, 'Petit', 'Alice', 'a.petit@startup-health.com', '0699887766', true, '1992-08-30');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (11, 'Roux', 'Luc', 'luc.roux@test.fr', '0677889900', true, '1965-01-10');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (12, 'Faure', 'Hélène', 'h.faure@wanadoo.fr', '0563443322', true, '1955-09-18');

-- 2. EXPERIMENTATIONS
INSERT INTO experimentation (id_experimentation, nom_experimentation, necessite_aidant, necessite_pro, description, date_debut_expe, date_fin_expe) VALUES (1, 'Presage', true, false, 'Détection chutes', '2026-06-01 10:00:00', '2026-06-30 10:00:00');
INSERT INTO experimentation (id_experimentation, nom_experimentation, necessite_aidant, necessite_pro, description, date_debut_expe, date_fin_expe) VALUES (2, 'Télé-Kiné', false, true, 'Rééducation', '2026-09-01 08:00:00', '2027-03-01 18:00:00');
INSERT INTO experimentation (id_experimentation, nom_experimentation, necessite_aidant, necessite_pro, description, date_debut_expe, date_fin_expe) VALUES (3, 'Nutri-Sénior', true, true, 'Nutrition', '2026-05-15 08:00:00', '2026-11-15 18:00:00');

-- 3. PROFILS
INSERT INTO non_professionnel (id_utilisateur, participation_expe) VALUES (1, 'OUI'), (2, 'OUI'), (4, 'OUI'), (5, 'OUI'), (6, 'OUI'), (11, 'OUI'), (12, 'OUI');
INSERT INTO utilisateur_moments (id_utilisateur, moment) VALUES (1, 'MATIN'), (1, 'SOIR'), (2, 'APRES_MIDI');
INSERT INTO professionnel (id_utilisateur, nom_fonction, structure, participation_expe) VALUES (3, 'Médecin', 'Cabinet Castres', 'OUI'), (7, 'Kiné', 'EHPAD', 'OUI'), (8, 'Infirmier', 'Cabinet Albi', 'OUI');

-- 4. INSCRIPTIONS (Utilisation de SENIOR/AIDANT et ACCEPTEE/REFUSEE/EN_ATTENTE)
INSERT INTO demande_inscription_expe (id_utilisateur, id_experimentation, role_pour_cette_expe, statut, date_demande) VALUES (1, 1, 'SENIOR', 'EN_ATTENTE', CURRENT_TIMESTAMP);
INSERT INTO demande_inscription_expe (id_utilisateur, id_experimentation, role_pour_cette_expe, statut, date_demande) VALUES (2, 1, 'AIDANT', 'ACCEPTEE', CURRENT_TIMESTAMP);
INSERT INTO demande_inscription_expe (id_utilisateur, id_experimentation, role_pour_cette_expe, statut, date_demande) VALUES (7, 2, 'AIDANT', 'ACCEPTEE', CURRENT_TIMESTAMP);
INSERT INTO demande_inscription_expe (id_utilisateur, id_experimentation, role_pour_cette_expe, statut, date_demande) VALUES (12, 3, 'SENIOR', 'REFUSEE', CURRENT_TIMESTAMP);

-- 5. SEQUENCES
ALTER TABLE utilisateur ALTER COLUMN id_utilisateur RESTART WITH 13;
ALTER TABLE experimentation ALTER COLUMN id_experimentation RESTART WITH 4;