CREATE TABLE parents (
  parent_id BIGSERIAL PRIMARY KEY,
  keycloak_user_id UUID NOT NULL UNIQUE,
  email VARCHAR(50) NOT NULL
);

CREATE TABLE trainers (
  trainer_id BIGSERIAL PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL
);

CREATE TABLE levels (
  level_id BIGSERIAL PRIMARY KEY,
  level_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE level_requirements (
  level_requirement_id BIGSERIAL PRIMARY KEY,
  level_id INTEGER NOT NULL
    REFERENCES levels(level_id),
  requirements TEXT,
  description TEXT
);

CREATE TABLE courses (
  course_id BIGSERIAL PRIMARY KEY,
  trainer_id INTEGER NOT NULL
    REFERENCES trainers(trainer_id),
  level_id INTEGER NOT NULL
    REFERENCES levels(level_id),
  course_name VARCHAR(50) NOT NULL,
  course_day VARCHAR(50) NOT NULL,
  course_time TIMESTAMP NOT NULL
);

CREATE TABLE swimmers (
  swimmer_id BIGSERIAL PRIMARY KEY,
  parent_id INTEGER NOT NULL
    REFERENCES parents(parent_id),
  course_id INTEGER
    REFERENCES courses(course_id),
  level_id INTEGER
    REFERENCES levels(level_id),
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  date_of_birth DATE NOT NULL
);
