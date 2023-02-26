# eXalt-Test

#Gestion de compte avec Spring Boot 3

Ce projet est une application Spring Boot 3 qui permet de gérer les opérations d'un compte. Elle utilise une base de données H2 pour stocker les informations relatives a un compte.

#Fonctionnalités

*Dépôt sur un compte

*Retrait sur un compte

*Consultation de solde

*Consultation de l'historique des transactions

#Prérequis

Java 17 ou supérieur


#Installation

Cloner le projet depuis GitHub

Lancer l'application en utilisant la commande 
```
mvn spring-boot:run
```
ou

```
docker build -t exalt-test:latest -f ./Dockerfile .
docker run -p 8080:8080 exalt-test:latest
```


#Utilisation

Une fois l'application lancée, un compte est automatiquement créé grâce à la classe CompteApplicationListener. Vous pouvez accéder à l'interface Swagger en ouvrant votre navigateur et en accédant à http://localhost:8080/swagger-ui/index.html. À partir de là, vous pouvez effectuer des opérations sur le compte existant et consulter l'historique des opérations effectuées sur le compte.

#Auteur

Ce projet a été créé par [Riadh TOUNAKTI]