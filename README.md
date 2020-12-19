<div align="center"> 

# [DSCatalog](https://leodelmiro-dscatalog.netlify.app/)

### Catalog Products CRUD

Aplicação CRUD de catálogo de produtos, usuários não logados podem ter acesso ao catálogo de produtos e usuários logados terão acesso as páginas de cadastro de produtos, usuários administradores também poderão cadastrar novos usuários.

[![front-end](https://img.shields.io/badge/front--end-react-blue)](https://github.com/leodelmiro/dscatalog-bootcamp-devsuperior/tree/main/front-web)
[![back-end](https://img.shields.io/badge/back--end-spring-green)](https://github.com/leodelmiro/dscatalog-bootcamp-devsuperior/tree/main/backend)
[![mobile](https://img.shields.io/badge/mobile-react%20native-lightskyblue)](https://github.com/leodelmiro/dscatalog-bootcamp-devsuperior/tree/main/mobile)

</div>

## Endpoints Back end

### Category
![Endpoints Category](https://i.imgur.com/YblcEhV.png)

### Product
![Endpoints Product](https://i.imgur.com/ZGc70fA.png)

### User
![Endpoints User](https://i.imgur.com/YPQM0dD.png)

## Layout web

![Home](https://i.imgur.com/JBIbkmj.png)

![Home Logged](https://i.imgur.com/lvqv2aV.png)

![Catalog](https://i.imgur.com/ka2fh7B.png)

![Login](https://i.imgur.com/x2BfcAY.png)

![Admin Page - User View](https://i.imgur.com/tdvSuRQ.png)

![Admin Page - Adm View](https://i.imgur.com/2HC7vZL.png)

![Product Register](https://i.imgur.com/I2GVrVs.png)

## Layout mobile

Em progresso ...

## Modelo conceitual

![Modelo Conceitual](https://i.imgur.com/gepyuMV.png)

# Tecnologias utilizadas

- Padrão de camadas
- Web service REST


## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- Swagger
- OAuth / JWT
- JUnit / Mockito
- Docker
- Travis

## Front end
- HTML / CSS / JS / TypeScript
- ReactJS
- React Navigation
- Axios
- React Content Loader
- React Hook Form
- React Testing Library / Jest

## Mobile
- React Native
- Expo
- React Navigation
- Axios
- Async Storage

## Implantação em produção
- Back end: Heroku
- Front end web: Netlify
- Banco de dados: Postgresql
- Container: Docker

# Como executar o projeto

## Back end
Pré-requisitos: Java 11

```bash
# clonar repositório
git clone https://github.com/leodelmiro/dscatalog-bootcamp-devsuperior

# entrar na pasta do projeto back end
cd backend

# executar o projeto
./mvnw spring-boot:run
```

## Back end com Docker (Rodando Banco H2)
Pré-requisitos: Docker

``` Terminal
docker run -p 80:8080 --name dscatalog-h2 leodelmiro/dscatalog:v1
```

## Front end web
Pré-requisitos: npm / yarn

```bash
# clonar repositório
git clone https://github.com/leodelmiro/dscatalog-bootcamp-devsuperior

# entrar na pasta do projeto front end web
cd front-web

# instalar dependências
yarn install

# executar o projeto
yarn start
```

## mobile
Pré-requisitos: npm / yarn / expo

```bash
# clonar repositório
git clone https://github.com/leodelmiro/dscatalog-bootcamp-devsuperior

# entrar na pasta do projeto front end web
cd mobile

# instalar dependências
yarn install

# executar o projeto
yarn start
```

# Autor

Leonardo Delmiro

https://www.linkedin.com/in/leodelmiro

