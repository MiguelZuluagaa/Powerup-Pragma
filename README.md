<div align="center">
  <h1>PROYECT POWERUP PRAGMA - LUIS MIGUEL ZULUAGA GIRALDO</h1>
</div>
<br />
<div align="center">
<h3 align="center">PRAGMA POWER-UP</h3>
  <p align="center">
    In this challenge you are going to design the backend of a system that centralizes the services and orders of a restaurant chain that has different branches in the city.
  </p>
</div>

### Built With

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
* ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
* ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
* ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)


<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these steps.

### Prerequisites

* JDK 17 [https://jdk.java.net/java-se-ri/17](https://jdk.java.net/java-se-ri/17)
* Gradle [https://gradle.org/install/](https://gradle.org/install/)
* MySQL [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)

### Recommended Tools
* IntelliJ Community [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
* Postman [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

### Installation

1. Clone 2 repository's
  ```sh
    git clone https://github.com/MiguelZuluagaa/Powerup-Pragma.git
  ```
  ```sh
    git clone https://github.com/MiguelZuluagaa/configs-data.git
  ```
2. Create a 2 new database in MySQL called "usuarios" and "plazoleta"
3. Update the path of previously cloned configurations (config-data):
  ```yml
  # MICROSERVICE:CONFIG-SERVICE --> resources/application.properties
    server.port= 9000
    spring.cloud.config.server.git.uri= file://<<YOUR RUTE IN THE SAME FORMAT WITH "\" >>
    spring.security.user.name=root
    spring.security.user.password=root
  ```
4. Update the database connection settings on the proyect configs-data.git
  ```yml
  # config-data/plazoleta-service and config-data/user-service
  spring:
  main:
    allow-bean-definition-overriding: true
  datasource:
    url: jdbc:mysql://localhost:${MYSQL_PORT:3307}/plazoleta
    username: ${MYSQL_USERNAME:root}
    password: ${MYSQL_PASSWORD:1234}
  ```
5. After the tables are created execute the next content to populate the database
```sql
  INSERT INTO `usuarios`.`role` (`id`, `description`, `name`) VALUES ('1', 'ROLE_ADMIN', 'ROLE_ADMIN');
  INSERT INTO `usuarios`.`role` (`id`, `description`, `name`) VALUES ('2', 'ROLE_PROPIETARIO', 'ROLE_PROPIETARIO');
  INSERT INTO `usuarios`.`role` (`id`, `description`, `name`) VALUES ('3', 'ROLE_EMPLEADO', 'ROLE_EMPLEADO');
  INSERT INTO `usuarios`.`role` (`id`, `description`, `name`) VALUES ('4', 'ROLE_CLIENTE', 'ROLE_CLIENTE');
  
  /* For a better understanding, the password for each user is the same value contained in the user's DNI. */
  INSERT INTO `usuarios`.`user` (`id`, `bird_date`, `dni`, `email`, `name`, `password`, `phone`, `surname`, `id_role`) VALUES ('1', '20-01-2001', '999', 'admin@gmail.com','admin',   '$10$JQSdrv00xsq/mpR9zd2TKOic.FPCetQJLKhBAQe188hOpFA8sFQM.', '123456789', 'admin', '1');
  INSERT INTO `usuarios`.`user` (`id`, `bird_date`, `dni`, `email`, `name`, `password`, `phone`, `surname`, `id_role`) VALUES ('8', '20-01-2001', '123', 'owner@gmail.com','admin', '$10$o4w4Bb/0/JV9UZvMOLHtiOivt3tq5p2akqbeWQaPJx5mVEZ7/HncG', '12345654', 'owner', '2');
  INSERT INTO `usuarios`.`user` (`id`, `bird_date`, `dni`, `email`, `name`, `password`, `phone`, `surname`, `id_role`) VALUES ('9', '20-01-2001', '333', 'employee@gmail.com','employee', '$10$d3S1HpQQufLKVdbvZDX7ieWBReCD0zJUVjR8qa/hh49zkDviCkYnq', '12345654', 'employee', '3');
  INSERT INTO `usuarios`.`user` (`id`, `bird_date`, `dni`, `email`, `name`, `password`, `phone`, `surname`, `id_role`) VALUES ('10', '20-01-2001', '444', 'customer@gmail.com','customer', '$10$5hJQzCjwr6i2fJuhEgwzuu6mqHx4UxbfvLNoZQ4UooGtKmpHgAS/i', '12345654', 'customer', '4');

  INSERT INTO `plazoleta`.`category` (`id`, `description`, `name`) VALUES ('1', 'ENTRANCE', 'ENTRANCE');
  INSERT INTO `plazoleta`.`category` (`id`, `description`, `name`) VALUES ('2', 'SNAKS', 'SNAKS');
  INSERT INTO `plazoleta`.`category` (`id`, `description`, `name`) VALUES ('3', 'APPETIZERS', 'APPETIZERS');
  INSERT INTO `plazoleta`.`category` (`id`, `description`, `name`) VALUES ('4', 'SOUPS', 'SOUPS');
  INSERT INTO `plazoleta`.`category` (`id`, `description`, `name`) VALUES ('5', 'MAIN COURSES', 'MAIN COURSES');
  INSERT INTO `plazoleta`.`category` (`id`, `description`, `name`) VALUES ('6', 'DESSERTS', 'DESSERTS');
  INSERT INTO `plazoleta`.`category` (`id`, `description`, `name`) VALUES ('7', 'DRINKS', 'DRINKS');
```
6. To execute the project you must run each microservice as follows: </br>
  6.1 Run ConfigServiceApplication </br>
  6.2 Run RegistryServiceApplication </br>
  6.3 Run UserMicroserviceApplication </br>
  6.4 Run PlazoletaMicroserviceApplication </br>
  6.5 Run TrackingMicroserviceApplication </br>
  6.6 Run MessengerMicroserviceApplication
6. Open Swagger UI and search the /user/ POST endpoint and create the user

<!-- USAGE -->
## Usage

1. Open Microservices user [http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html) in your web browser
2. Open Microservices plazoleta [http://localhost:8091/swagger-ui/index.html](http://localhost:8091/swagger-ui/index.html) in your web browser
3. Open Eureka Server [http://localhost:8099](http://localhost:8099) in your web browser

<!-- ROADMAP -->
## Tests

- Right-click the test folder and choose Run tests with coverage

