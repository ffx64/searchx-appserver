===========================================================================================
                                       SearchX API                                       
===========================================================================================

This module serves as the main interface for interacting with the SearchX system.
It exposes RESTful endpoints to query, manage, and retrieve leak data stored in
the database, enabling secure and efficient integration with external tools and
platforms.

This project is intended solely for educational purposes. Use for any illegal or
unauthorized activities is strictly prohibited. Please use it responsibly and
ethically.

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

2. Configure the `MainDataSourceConfig.java` and `CombolistDataSourceConfig.java`
   with your database credentials.
    - As per https://github.com/ffx64/searchx-migrations

3. Run the project:
   ./mvnw spring-boot:run

4. Visit:
   http://localhost:8080/api/v1/*

-------------------------------------------------------------------------------------------
                                       API Routes v1                               
-------------------------------------------------------------------------------------------

AUTHENTICATION 
==============================
POST   /api/v1/auth/login           -> Login and generate tokens  
POST   /api/v1/auth/refresh         -> Refresh the token using Refresh-Token  
POST   /api/v1/auth/logout          -> Invalidate the current token  
GET    /api/v1/auth/me              -> Get the authenticated account's information  
PUT    /api/v1/auth/me              -> Update the authenticated account's information  

USERS 
==============================
GET    /api/v1/users                -> Get all users  

AGENTS 
==============================
POST   /api/v1/agents               -> Create a new agent  
PUT    /api/v1/agents/{id}          -> Update an existing agent  
GET    /api/v1/agents               -> Get all agents  
GET    /api/v1/agents/{id}          -> Get agent by ID  
DELETE /api/v1/agents/{id}          -> Delete agent by ID  

COMBOLIST - METADATA 
==============================
GET    /api/v1/combolist/metadata/{id}      -> Get metadata by ID  
GET    /api/v1/combolist/metadata/page/{n}  -> Get paginated metadata (10 per page)  

COMBOLIST - DATA 
==============================
GET    /api/v1/combolist/data/{id}           -> Get entry by ID with metadata  
GET    /api/v1/combolist/data/email/{email}  -> Search by email  
GET    /api/v1/combolist/data/username/{u}   -> Search by username  
GET    /api/v1/combolist/data/password/{p}   -> Search by password  
GET    /api/v1/combolist/data/domain/{d}     -> Search by domain  
GET    /api/v1/combolist/data/search/{input} -> Search for similar input (email or username)  

-------------------------------------------------------------------------------------------
                                    Project Structure                                 
-------------------------------------------------------------------------------------------

com.ffx64.searchx_api/
├── infra/          <- security, CORS, beans, JWT, global handler, etc
├── controller/     <- receives requests, calls the service, returns response
├── dto/            <- classes for transporting data
├── entity/         <- JPA models, with @Entity annotation
├── exception/      <- custom exceptions
├── repository/     <- interface with PostgreSQL
├── service/        <- business logic
└── utils/          <- helpers, password, hash, formatter, etc

-------------------------------------------------------------------------------------------
                                         Notes                                         
-------------------------------------------------------------------------------------------

- Part of the SearchX project (along with indexer, migrations, etc)
- Pull requests are welcome!