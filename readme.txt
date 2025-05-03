===========================================================================================
                                       SearchX API                                       
===========================================================================================

This module serves as the main interface for interacting with the SearchX system.
It exposes RESTful endpoints to query, manage, and retrieve leak data stored in
the database, enabling secure and efficient integration with external tools and
platforms.

-------------------------------------------------------------------------------------------
                                        Features                                        
-------------------------------------------------------------------------------------------

- RESTful endpoints created with Spring Boot
- Integration with PostgreSQL databases
- Management of indexed leaks (combos, credentials, chats, etc.)
- Filtering and querying of data

-------------------------------------------------------------------------------------------
                                       Prerequisites                                      
-------------------------------------------------------------------------------------------

- Java 17+
- Spring Boot 3.x
- PostgreSQL
- Maven

-------------------------------------------------------------------------------------------
                                          Usages                                          
-------------------------------------------------------------------------------------------

1. Clone the repository:
   git clone https://github.com/ffx64/searchx-api

2. Configure the `SearchxDataSourceConfig.java` and `CombolistDataSourceConfig.java`
   with your database credentials.
    - As per https://github.com/ffx64/searchx-migrations

3. Run the project:
   ./mvnw spring-boot:run

4. Visit:
   http://localhost:8080/v1/api/*

-------------------------------------------------------------------------------------------
                                     Project Structure                                 
-------------------------------------------------------------------------------------------

com.ffx64.searchx_api/
├── infra/          <- security, CORS, beans, JWT, etc
├── controller/     <- receives requests, calls the service, returns response
├── dto/            <- classes for transporting data
├── entity/         <- JPA models, with @Entity annotation
├── exception/      <- custom exceptions + global handlers
├── repository/     <- interface with PostgreSQL
├── service/        <- business logic
└── utils/          <- helpers, password, hash, formatter, etc

-------------------------------------------------------------------------------------------
                                           Notes                                         
-------------------------------------------------------------------------------------------

- Part of the SearchX project (along with indexer, migrations, etc)
- Pull requests are welcome!