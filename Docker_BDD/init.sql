-- Création de la table "Movie" pour représenter les films
CREATE TABLE movie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    film_description TEXT,
    publication_year INT NOT NULL
);

-- Création de la table "Actor" pour représenter les acteurs
CREATE TABLE actor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    actor_last_name VARCHAR(255) NOT NULL,
    actor_name VARCHAR(255) NOT NULL
    actor_birth_date VARCHAR(10) NOT NULL
);

-- Création de la table "Author" pour représenter les auteurs (réalisateurs)
CREATE TABLE author (
    id INT AUTO_INCREMENT PRIMARY KEY,
    author_last_name VARCHAR(255) NOT NULL,
    author_name VARCHAR(255) NOT NULL
    author_birth_date VARCHAR(10) NOT NULL
);

-- Création de la table de liaison "ActorMovie" pour les relations many-to-many avec les acteurs
CREATE TABLE actor_movie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT,
    actor_id INT,
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE,
    FOREIGN KEY (actor_id) REFERENCES actor(id) ON DELETE CASCADE
);

-- Création de la table de liaison "AuthorMovie" pour les relations many-to-many avec les auteurs
CREATE TABLE author_movie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT,
    author_id INT,
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE
);
