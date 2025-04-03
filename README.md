# Order :: Service for Order Management

Project Challenge M - A

---

## Requisitos

O projeto faz uso de:
`Docker version 27.4.0`
`Docker Compose version v2.31.0`
`Spring Boot 3.4.4`
`JDK 21`
`Maven 3.9.6`

- [Docker](https://docs.docker.com/get-started/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Flyway](https://flywaydb.org/)
- [PostgreSQL](https://www.postgresql.org/)

---

## Instalação

### Clone o repositório

```bash
git clone git@github.com:ewertondias/order.git
```

### Navegue para o diretório do projeto

```bash
cd order
```

### Compilar
```bash
mvn clean install
```

### Executar os testes
```bash
mvn clean verify
```

### Instalação do ambiente via docker
Acessar a pasta onde se encontra o arquivo docker-compose.yml 
```
cd order/env/docker/
```

Subir RabbitMQ e PostgreSQL
```
docker compose up -d
```

---

## Banco de dados local da aplicação

`PostgreSQL`
- URL: jdbc:postgresql://localhost:5432/order
- usuario: postgres
- senha: order


## Gerenciador do RabbitMQ
http://localhost:15672/

## Api rest para consulta de pedidos
http://localhost:8080/swagger-ui/index.html

## Solução proposta
![Solucao proposta](https://github.com/user-attachments/assets/803bad05-6470-4241-9410-745654ccc369)
