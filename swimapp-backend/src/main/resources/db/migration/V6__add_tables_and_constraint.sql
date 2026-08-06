ALTER TABLE trainers
ADD COLUMN email VARCHAR(30);

ALTER TABLE trainers
ADD COLUMN username VARCHAR(30);

ALTER TABLE parents
ADD CONSTRAINT uk_parents_keycloak
UNIQUE(keycloak_user_id);
