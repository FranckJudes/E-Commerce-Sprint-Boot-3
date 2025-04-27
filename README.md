# Projet E-Commerce Spring Boot Microservices

Architecture microservices complète pour une application e-commerce avec Spring Boot 3.

## Architecture

Le projet est composé des microservices suivants :

- **Config Server** : Gestion centralisée de la configuration
- **Discovery Service** : Registre de services Eureka 
- **API Gateway** : Point d'entrée unique de l'API
- **Customer Service** : Gestion des clients et authentification
- **Product Service** : Gestion du catalogue des produits
- **Order Service** : Gestion des commandes
- **Finance Service** : Gestion des paiements et transactions
- **Notification Service** : Envoi de notifications (email, SMS, etc.)

## Prérequis

- JDK 17+
- Maven
- Docker et Docker Compose
- Un compte Docker Hub

## Structure du projet

```
E-Commerce-Sprint-Boot-3/
├── .github/
|    |── workflows/
|    |   └── docker.yml
├── .vscode/
├── README.md
├── docker-compose.yml
├── .env
├── git-helper.sh
└── services/
    ├── config-server/
    ├── discovery/
    ├── customer/
    ├── Product/
    ├── finances/
    ├── order/
    ├── notification/
    ├── gateway/
```

## Déploiement local

1. Cloner le dépôt
   ```bash
   git clone https://github.com/votre-nom/E-Commerce-Sprint-Boot-3.git
   cd E-Commerce-Sprint-Boot-3
   ```

2. Configurer le fichier `.env` avec vos propres valeurs
   ```bash
   cp .env.example .env
   # Modifiez le fichier .env avec vos informations
   ```

3. Démarrer les services avec Docker Compose
   ```bash
   docker-compose up -d
   ```

4. Vérifier l'état des services
   ```bash
   docker-compose ps
   ```

5. Accéder aux interfaces
   - Eureka : http://localhost:8761
   - API Gateway : http://localhost:8080
   - Swagger UI : http://localhost:8080/swagger-ui.html

## Déploiement sur VPS

Le projet est configuré pour un déploiement automatique via GitHub Actions. Après avoir poussé vos modifications sur la branche principale, le workflow suivant est exécuté :

1. Build de tous les services
2. Push des images Docker sur Docker Hub
3. Déploiement sur votre VPS via SSH

### Configuration requise pour GitHub Actions

Ajoutez les secrets suivants dans votre dépôt GitHub :

- `DOCKER_USERNAME` : Nom d'utilisateur Docker Hub
- `DOCKER_PASSWORD` : Mot de passe Docker Hub
- `SSH_PRIVATE_KEY` : Clé SSH privée pour accéder au VPS
- `HOST` : Adresse IP ou nom d'hôte de votre VPS
- `SSH_USER` : Nom d'utilisateur SSH
- `TARGET_DIR` : Répertoire cible sur le VPS

## Développement

Pour ajouter un nouveau service :

1. Créez un nouveau dossier dans le répertoire `services/`
2. Ajoutez un fichier `Dockerfile` dans le dossier du service
3. Mettez à jour le fichier `docker-compose.yml` pour inclure le nouveau service
4. Mettez à jour le workflow GitHub Actions dans `.github/workflows/docker.yml`

## Surveillance et logs

Pour consulter les logs d'un service spécifique :
```bash
docker-compose logs -f nom-du-service
```

## Maintenance

Pour mettre à jour tous les services :
```bash
docker-compose down
git pull
docker-compose up -d --build
```

## Sauvegardes

Les données persistantes sont stockées dans des volumes Docker. Pour sauvegarder ces données :
```bash
docker volume ls
docker volume inspect nom-du-volume
# Sauvegardez le contenu du chemin indiqué
```

## Licence
