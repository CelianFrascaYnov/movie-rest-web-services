# Projet MovieDB

Le projet MovieDB est une application de gestion de films, d'acteurs et d'auteurs. Cette application permet aux utilisateurs de consulter, ajouter, mettre à jour et supprimer des films, des acteurs et des auteurs. Elle offre également des fonctionnalités de recherche avancée pour trouver des films basés sur des acteurs et des auteurs.

## Technologies utilisées

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## Prérequis

- Base de données MySQL : Il est nécessaire d'installer auparavant la base de données MySQL via le dossier Docker contenu dans le projet. Pour cela, il faut se placer dans le dossier Docker_BDD et lancer la commande suivante :

```dockerfile
docker compose up
```

## Installation

1. Clônez le dépôt depuis GitHub :

   ```bash
   git clone https://github.com/votre-utilisateur/MovieDB.git

2. Naviguez vers le répertoire du projet :

   ```bash
   cd MovieDB

3. Build et lancez le projet avec Maven :

   ```bash
   mvn spring-boot:run

Le projet devrait maintenant être en cours d'exécution à l'adresse http://localhost:8080.

## Utilisation
Pour utiliser l'application vous pouvez accéder aux API REST pour interagir avec l'application à l'aide de Postman. Le fichier Postman pour accéder à la librairie est disponible dans le fichier WORD. 
