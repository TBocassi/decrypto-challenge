# Decrypto Challenge API

##

## Structure
Group subpackages: controllers, services, repositories, model, utils, mappers, configs,security and exceptions.
## Project Structure
* **controllers**: Controllers to handle HTTP requests.
* **services**: Business logic of the microservice.
* **repositories**: Data access interfaces.
* **model**: Classes representing the data.
* **utils**: Utility functions and helpers.
* **mappers**: Classes for mapping entities.
* **configs**: Application configurations.
* **security**: Security configurations and filters.
* **exceptions**: Custom exception handling.
## Build application
To compile:
```shell script
$ mvn clean package
```

## Boot application
Default port is 8080.

To run application:
```shell script
$ java -Dspring.profiles.active=local -jar challenge-0.0.1-SNAPSHOT.jar
```

## Build management
* App is using maven 3.8.1 for build the application.

## Java
* Java 17.0.2 is assumed as the minimum version.
* You can download it from https://adoptium.net/temurin/archive

## Logging
* Pending feature (implement kibana)


## OpenAPI and Swagger
* For API documentation the application uses springdoc-openapi-ui which supports OpenAPI v3.
* See <code>ClientController</code> and <code>Client</code> for an example on how to use documentation annotations.
* Swagger UI will be available at: http://{HOST}:{PORT}/decrypto-challenge/swagger-ui/index.html
  * Example: http://localhost:8080/decrypto-challenge/swagger-ui/index.html


## Relational Databases
* For PostgresSql import: org.springframework.boot:spring-boot-starter-data-jpa
* Download and documentation: https://www.postgresql.org/

## PostgresSql and JPA
* Credentials are located in <code>application-local.properties</code>.
* For PostgresSql JPA database sample endpoint see <code>ClientController</code>
  * Example: GET request to: http://localhost:{your-port}/decrypto-challenge/client


  
## Unit Tests
* junit-jupiter and mockito dependencies are declared for unit testing.
* Implement SonaQube is a pending feature.


## Coverage
* jacoco dependencies are declared for coverage.
* To generate coverage file:
  * <code>mvn clean test</code>
* Coverage file generated in /target/site/jacoco/index.html     
* Configure POM to exclude main class, POJOs, autogenerated interfaces implementation (mapstruct), repository interfaces.


## Auth endpoints
* JWT Bearer token dependencies are declared for authetication
* Admin user created by the first boot application
* To authenticate with admin user and get bearer token:
  * POST http://localhost:{your-port}/decrypto-challenge/authenticate
```shell script
{
  "username" : "admin",
  "username" : "admin"
}
```
* Response:
```shell script
{
  "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJBRE1JTl9ST0xFIiwiVVNFUl9ST0xFIl0sImV4cCI6MTcyODA2NDA4MSwiaWF0IjoxNzI4MDYwNDgxLCJ1c2VybmFtZSI6ImFkbWluIn0.0Vn2-jQ5XbY-q9lL5zW4vfdCU87T9mUdHZ722ICWgUI"
}
```
* To use token for endpoints set the header:
```shell script
{
  "Authorization" : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJBRE1JTl9ST0xFIiwiVVNFUl9ST0xFIl0sImV4cCI6MTcyODA2NDA4MSwiaWF0IjoxNzI4MDYwNDgxLCJ1c2VybmFtZSI6ImFkbWluIn0.0Vn2-jQ5XbY-q9lL5zW4vfdCU87T9mUdHZ722ICWgUI"
}
```