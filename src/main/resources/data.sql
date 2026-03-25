-- 1. LES UTILISATEURS (Date de naissance est ici maintenant)
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (1, 'Boulanger', 'Yvette', 'yvette.b81@orange.fr', '0563112233', true, '1945-05-12');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (2, 'Boulanger', 'Marc', 'marc.boulanger@gmail.com', '0612345678', true, '1975-10-20');
INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, telephone, consentement, date_naissance) VALUES (3, 'Lemaire', 'Sophie', 'dr.lemaire@sante.fr', '0563445566', true, '1982-03-15');

-- 2. LES EXPERIMENTATIONS
INSERT INTO experimentation (id_experimentation, nom_experimentation, necessite_aidant, necessite_pro, description, date_debut_expe, date_fin_expe) VALUES (1, 'Presage', true, false, 'Test simple', '2026-06-01 10:00:00', '2026-06-30 10:00:00');

-- 3. LES PROFILS (On a enlevé date_naissance ici)

INSERT INTO non_professionnel (id_utilisateur, participation_expe) VALUES (1, 'OUI');
INSERT INTO non_professionnel (id_utilisateur, participation_expe) VALUES (2, 'OUI');

INSERT INTO utilisateur_moments (id_utilisateur, moment) VALUES (1, 'MATIN');
INSERT INTO utilisateur_moments (id_utilisateur, moment) VALUES (1, 'SOIR'); -- Yvette est dispo matin ET soir
INSERT INTO utilisateur_moments (id_utilisateur, moment) VALUES (2, 'APRES_MIDI');
INSERT INTO professionnel (id_utilisateur, nom_fonction, structure, participation_expe) VALUES (3, 'Médecin Généraliste', 'Cabinet Médical Castres', 'OUI');

-- 4. LES INSCRIPTIONS
INSERT INTO demande_inscription_expe (id_utilisateur, id_experimentation, role_pour_cette_expe, statut, date_demande) VALUES (1, 1, 'SENIOR', 'EN_ATTENTE', CURRENT_TIMESTAMP);
INSERT INTO demande_inscription_expe (id_utilisateur, id_experimentation, role_pour_cette_expe, statut, date_demande) VALUES (2, 1, 'AIDANT', 'ACCEPTEE', CURRENT_TIMESTAMP);
-- Pour H2 : On force la séquence à démarrer après nos inserts manuels
ALTER TABLE utilisateur ALTER COLUMN id_utilisateur RESTART WITH 4;