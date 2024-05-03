# Desafio Java Pleno Omnilink


## Relatório de Tecnologias Utilizadas no Projeto

### Spring Framework:
- Spring Boot Starter Parent
- Spring Boot Starter Actuator
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- Spring Boot Starter Web
- Spring Boot Starter Web Services
- Spring Boot Starter Test
- Spring Boot Starter Cache (Redis)
- Spring Boot Starter Data Redis
- Spring Boot Starter Security
- Spring Boot Starter OAuth2 Resource Server
- Spring Boot Starter WebFlux

### Outras Bibliotecas:
- Project Lombok
- Flyway Core
- Flyway MySQL (Migração e atualização DB)
- H2 Database (Testes de integração)
- slf4j (logs personalizados 5W, os 5 porques)

### Ferramentas de Documentação e Teste:
- Springdoc OpenAPI Starter WebMVC UI
- Junit
- AssertJ
- TestRestTemplate (IT)

## Instalação

- 1 - clone
``` bash
git clone https://github.com/giovaner10/p.git
```

- 2 - DDL (MySql)
```sql

CREATE DATABASE IF NOT EXISTS CHALLANGE;

CREATE TABLE IF NOT EXISTS COSTUMER (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    costumer_type VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS VEHICLE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plate VARCHAR(255) UNIQUE NOT NULL,
    model VARCHAR(255) NOT NULL,
    model_year INT NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    registration_date DATE NOT NULL,
    brand VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    fuel_type VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    costumer INT NOT NULL,
    FOREIGN KEY (costumer) REFERENCES Costumer(id)
);


CREATE TABLE IF NOT EXISTS USERS (
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);

-- Password: password
INSERT INTO USERS (username, password) VALUES ('username', '$2a$10$GiseHkdvwOFr7A9KRWbeiOmg/PYPhWVjdm42puLfOzR/gIAQrsAGy');
```

- 3 - Start Cache with docker
``` bash
docker run --name my-redis -d -p 6379:6379 redis
```

- 3 - Insira no application.yml seu username e senha (MySql)
```sql 
  datasource:
    url: jdbc:mysql://localhost:[port]/challange
    username: XXXX
    password: XXXX
``` 



