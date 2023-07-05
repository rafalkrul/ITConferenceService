# ITConferenceService


## Technologies

- Java 8
- Spring Framework 2.7.12
- PostgreSql



## How to Run
1. Install Java and Docker.
2. Clone the repository:
   > https://github.com/rafalkrul/ITConferenceService
3. Create a configuration file at src/main/resources/application.properties with the following content:
  ```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=username
spring.datasource.password=password

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jooq.sql-dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

4. Navigate to ITConferenceService/docker directory and run the following command:

    ``` docker-compose up -d ```

5. Open the project in IntelliJ from the ITConferenceService directory and run the server.

## Usage

The project includes a `Postman.txt` file that contains sample commands along with DTO (Data Transfer Object) examples.

