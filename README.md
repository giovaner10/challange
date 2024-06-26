# Desafio Java Pleno Omnilink


## Relatório de Tecnologias Utilizadas no Projeto

### Spring Framework:
- Spring Boot Starter Parent
- Spring Boot Starter Actuator (Status)
- Spring Boot Starter Data JPA + Hibernate
- Spring Boot Starter Validation
- Spring Boot Starter Web
- Spring Boot Starter Web Services
- Spring Boot Starter Test
- Spring Boot Starter Cache (Redis)
- Spring Boot Starter Data Redis
- Spring Boot Starter Security
- Spring Boot Starter OAuth2 Resource Server + JWT Token
- Spring Boot Starter WebFlux (Validar localidade)

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

- 3 - Insira no application.yml seu username e senha (MySql)
```sql 
  datasource:
    url: jdbc:mysql://localhost:[port]/challange
    username: XXXX
    password: XXXX
``` 

- 4 - Spring doc (Swagger)
```sql 
http://localhost:8080/swagger-ui/index.html

username: username
pass: password
``` 

- 5 - Collections (Postman/Insomnia)
```sql 
path: collection/challange.postman_collection.json
``` 

- 6 - Inciar Cache Redis com docker
``` bash
docker run --name my-redis -d -p 6379:6379 redis
```

*START APPLICATION*

- 7 - Verificar status
``` bash
http://localhost:8080/actuator
http://localhost:8080/actuator/health
```


### Logs exemplo da saida (path: logs/app.log)
```sql 
2024-05-01 19:51:01.057 [INFO ] [http-nio-8080-exec-3] b.c.o.c.service.VehicleServiceImpl - user: teste@gmail.com action: init save vehicle plate/user: string/0
2024-05-01 19:52:30.546 [INFO ] [http-nio-8080-exec-5] b.c.o.c.service.VehicleServiceImpl - user: teste@gmail.com action: init save vehicle plate/user: string/0
2024-05-01 19:52:46.570 [INFO ] [http-nio-8080-exec-6] b.c.o.c.service.VehicleServiceImpl - user: teste@gmail.com action: init save vehicle plate/user: string/0
2024-05-01 19:52:47.191 [ERROR] [http-nio-8080-exec-6] b.c.o.c.service.CostumerServiceImpl - user: teste@gmail.com - Fail to find costumer is not present id: 0 - error: class br.com.omnilink.challange.exception.model.ObjectNotFoundException
2024-05-01 19:52:53.954 [INFO ] [http-nio-8080-exec-8] b.c.o.c.service.VehicleServiceImpl - user: teste@gmail.com action: init save vehicle plate/user: string/1
2024-05-01 19:52:54.089 [ERROR] [http-nio-8080-exec-8] b.c.o.c.service.CostumerServiceImpl - user: teste@gmail.com - Fail to find costumer is not present id: 1 - error: class br.com.omnilink.challange.exception.model.ObjectNotFoundException
2024-05-01 19:53:00.546 [INFO ] [http-nio-8080-exec-7] b.c.o.c.service.VehicleServiceImpl - user: teste@gmail.com action: init save vehicle plate/user: string/2
2024-05-01 19:53:09.624 [INFO ] [http-nio-8080-exec-10] b.c.o.c.service.CostumerServiceImpl - user: teste@gmail.com - action: find all.
2024-05-01 19:53:36.675 [INFO ] [http-nio-8080-exec-2] b.c.o.c.service.VehicleServiceImpl - user: teste@gmail.com action: init save vehicle plate/user: string/2
``` 

### Testes unitários

![Texto Alternativo](https://github.com/giovaner10/challange/blob/main/images/img.png?raw=true)
