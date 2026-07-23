ALTER TABLE trainers
ADD COLUMN keycloak_user_id UUID;

ALTER TABLE trainers
ADD CONSTRAINT trainers_keycloak_user_id_unique UNIQUE (keycloak_user_id);
