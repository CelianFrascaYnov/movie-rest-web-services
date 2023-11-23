# Utiliser une image de base Java
FROM openjdk:21

COPY wait-for-mysql.sh /wait-for-mysql.sh
RUN chmod +x /wait-for-mysql.sh

# Ajouter un volume pour stocker temporairement les données
VOLUME /tmp

# Ajouter le fichier .jar de votre application
ADD target/ApiRest-0.0.1-SNAPSHOT.jar ApiRest-0.0.1-SNAPSHOT.jar

# Exposer le port sur lequel votre application s'exécute
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["/wait-for-mysql.sh", "db", "java", "-jar", "/ApiRest-0.0.1-SNAPSHOT.jar"]
