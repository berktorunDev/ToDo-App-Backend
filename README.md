# Todo App Backend

## Project Overview

This repository contains the backend implementation of a Todo application. The backend is structured as follows:

### Directory Structure

- **../config:** Configuration classes specific to beans used in the project.
- **../controller:** Controller classes responsible for handling incoming requests.
- **../dto:** Data Transfer Object (DTO) models used for shaping responses.
- **../model:** Entity and enum classes representing data structures.
- **../repository:** Repository classes associated with database entities.
- **../script:** Runnable classes used during the application startup process.
- **../service:** REST service layer classes that encapsulate business logic.
- **../util:** Custom utility classes and tools used within the application.
- **../resources/sql:** SQL scripts available for database operations within the script package.
- **../resources/application.properties:** Configuration file for application-specific properties.
- **../resources/log4j2.xml:** Configuration file for logging settings.
- **docker-compose.yml:** Docker configuration for external services used by the application.

### Technologies Used

The project utilizes the following technologies and their respective purposes:

- **Spring Security:** Provides authentication and authorization capabilities. Configuration is done in `../config/SecurityConfig`.
- **PostgreSQL:** Serves as a relational database to store data related to certain entities. It is containerized using Docker.
- **Redis:** Used for caching enum entities. Redis is also containerized via Docker and configured in `../config/RedisConfig`. For usage scenarios, refer to `../util/service/redis`.
- **RabbitMQ:** Utilized for sending notifications to users through a message queue. RabbitMQ is containerized using Docker and configured in `../config/RabbitMQConfig`. Since there is no integrated frontend in the project, notifications are logged in a specific format to the console. Usage scenarios can be found in `../util/service/rabbitmq`.

### Getting Started

Follow these steps to run the project:

1. **Clone the project:**
   ```bash
   git clone https://github.com/berktorunDev/ToDo-App-Backend.git
   cd todo-app-backend

2. **Start the required services using Docker Compose:**
    ```bash
    docker-compose up

## Application Startup

3. **Application Startup:**
   - Uncomment the lines in the `../script/StartupScript` class that are meant to run during the initial startup of the application. These lines are used for setting up the application for the first time. Don't forget to comment them back out once the application is running successfully.
   - Run the project. You can start the project from your IDE or use the following command in the terminal:
     ```bash
     mvn spring-boot:run
     ```

## Authentication Setup

4. **Authentication Setup:**
   - After the project has successfully started, configure authentication settings using a tool like Postman or Thunder Client:
     - Basic Authentication will be used.
     - Set up your authentication credentials using the following properties in the `application.properties` file:
       ```properties
       spring.security.user.password=your_desired_password
       spring.security.user.name=your_desired_username
       ```

## Testing Endpoints

5. **Testing Endpoints:**
   - Once the authentication setup is complete, you can make requests using the following URL format:
     - Example URL: `http://localhost:8080/{controller_path}/{controller_method}`
     - Some endpoints may require specific parameters or request bodies. For more details, refer to the `../controller` package.