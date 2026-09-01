-- Run this in MySQL Workbench (or `mysql -u root -p < schema.sql`) if you
-- want to create the database/table by hand instead of letting Hibernate's
-- ddl-auto: update do it automatically on app startup.

CREATE DATABASE IF NOT EXISTS snake_db;
USE snake_db;

CREATE TABLE IF NOT EXISTS snake (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    common_name         VARCHAR(255),
    scientific_name     VARCHAR(255),
    family              VARCHAR(255),
    region              VARCHAR(255),
    venomous            BOOLEAN,
    venom_type          VARCHAR(50),   -- NEUROTOXIC, HEMOTOXIC, CYTOTOXIC, MYOTOXIC, NONE
    danger_level        VARCHAR(50),   -- NONE, LOW, MODERATE, HIGH, EXTREME
    antivenom_available BOOLEAN,
    description         VARCHAR(2000)
);

-- A few example rows so you have something to look at before your dataset arrives
INSERT INTO snake (common_name, scientific_name, family, region, venomous, venom_type, danger_level, antivenom_available, description)
VALUES
('King Cobra', 'Ophiophagus hannah', 'Elapidae', 'South & Southeast Asia', TRUE, 'NEUROTOXIC', 'EXTREME', TRUE, 'Longest venomous snake in the world; bite can deliver enough neurotoxin to kill an elephant.'),
('Ball Python', 'Python regius', 'Pythonidae', 'West & Central Africa', FALSE, 'NONE', 'NONE', FALSE, 'Non-venomous constrictor, popular as a docile pet.'),
('Inland Taipan', 'Oxyuranus microlepidotus', 'Elapidae', 'Australia', TRUE, 'NEUROTOXIC', 'EXTREME', TRUE, 'Considered the most venomous snake by LD50, though reclusive and bites are rare.');
