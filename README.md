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
3. Create a 2 new database in MySQL called "usuarios" and "plazoleta"
4. Update the database connection settings on the proyect configs-data.git
   ```yml
   # config-data/plazoleta-service and config-data/user-service
   spring:
    main:
      allow-bean-definition-overriding: true
    datasource:
      url: jdbc:mysql://localhost:<<YOUR_MYSQL_PORT>>/plazoleta
      username: <<YOUR_USERNAME>>
      password: <<YOUR_PASSWORD>>
   ```
5. After the tables are created execute the next content to populate the database
```sql
  INSERT INTO `usuarios`.`role` (`id`, `description`, `name`) VALUES ('1', 'ROLE_ADMIN', 'ROLE_ADMIN');
  INSERT INTO `usuarios`.`role` (`id`, `description`, `name`) VALUES ('2', 'ROLE_PROPIETARIO', 'ROLE_PROPIETARIO');
  INSERT INTO `usuarios`.`role` (`id`, `description`, `name`) VALUES ('3', 'ROLE_EMPLEADO', 'ROLE_EMPLEADO');
  INSERT INTO `usuarios`.`role` (`id`, `description`, `name`) VALUES ('4', 'ROLE_CLIENTE', 'ROLE_CLIENTE');
```
7. Open Swagger UI and search the /user/ POST endpoint and create the user

<!-- USAGE -->
## Usage

1. To execute the project you must run each microservice as follows: 
  1.1 Run ConfigServiceApplication
  1.2 Run RegistryServiceApplication
  1.3 Run UserMicroserviceApplication
  1.4 Run PlazoletaMicroserviceApplication
3. Open Microservices user [http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html) in your web browser
4. Open Microservices plazoleta [http://localhost:8091/swagger-ui/index.html](http://localhost:8091/swagger-ui/index.html) in your web browser
5. Open Eureka Server [http://localhost:8099](http://localhost:8099) in your web browser

<!-- ROADMAP -->
## Tests

- Right-click the test folder and choose Run tests with coverage

