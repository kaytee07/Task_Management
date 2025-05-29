# TaskManager

TaskManager is a Spring Boot application with a MySQL database, containerized using Docker. This README provides instructions to set up and run the application locally.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Example `docker-compose.yml`](#example-docker-composeyml)
- [Troubleshooting](#troubleshooting)
- [Notes](#notes)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Ensure you have the following installed:
- [Docker](https://docs.docker.com/get-docker/) (version 20.10 or higher)
- [Docker Compose](https://docs.docker.com/compose/install/) (version 2.0 or higher)
- [Git](https://git-scm.com/downloads)

## Setup Instructions

Follow these steps to set up and run the application locally:

1. **Clone the Repository**
   ```bash
   git clone git@github.com:kaytee07/Task_Management.git
   cd Task_Management
   ```


2. **Build and Run the Application**
    - Use Docker Compose to build and start the Spring Boot app and MySQL database:
      ```bash
      docker-compose up --build
      ```
    - This command builds the Spring Boot application image and starts both the app and MySQL containers.
    - Wait for the application to start (you’ll see logs indicating the Spring Boot app is running).

3. **Access the Application**
    - The Spring Boot app runs on `http://localhost:8080` by default.
    - Open your browser or use a tool like `curl` to test the app:
      ```bash
      curl http://localhost:8080
      ```
    - [Optional: Add specific endpoints, e.g., `/api/hello`, if applicable.]

4. **Access MySQL (Optional)**
    - The MySQL database is available on `localhost:3306` (or the port specified in `docker-compose.yml`).
    - Connect using a MySQL client (e.g., MySQL Workbench, DBeaver, or CLI):
      ```bash
      mysql -h localhost -P 3306 -u your-username -p
      ```
      Enter the password from your `.env` file or the default in `docker-compose.yml`.

5. **Stop the Application**
    - To stop the containers, press `Ctrl+C` in the terminal running `docker-compose up`.
    - To remove the containers (data persists in volumes):
      ```bash
      docker-compose down
      ```

## Example `docker-compose.yml`

For reference, here’s a sample `docker-compose.yml` used for this setup:

```yaml
version: '3.8'
services:
  app:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/your-database-name
      - SPRING_DATASOURCE_USERNAME=your-username
      - SPRING_DATASOURCE_PASSWORD=your-password
    depends_on:
      - mysql
  mysql:
    image: mysql:8.0
    ports:
      - "3306:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=your-root-password
      - MYSQL_DATABASE=your-database-name
      - MYSQL_USER=your-username
      - MYSQL_PASSWORD=your-password
    volumes:
      - mysql-data:/var/lib/mysql
volumes:
  mysql-data:
```

### Example `Dockerfile`

If you don’t have a `Dockerfile`, here’s a basic one for your Spring Boot app:

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/your-app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## Troubleshooting

- **MySQL Connection Issues**: Ensure the `SPRING_DATASOURCE_URL` matches the MySQL service name (`mysql`) and database name.
- **Port Conflicts**: If port `8080` or `3306` is in use, modify the `ports` in `docker-compose.yml` (e.g., `"8081:8080"`).
- **Logs**: Check container logs for errors:
  ```bash
  docker-compose logs
  ```

## Notes

- The MySQL data persists in a Docker volume (`mysql-data`) and won’t be lost when containers are stopped.
- To reset the database, remove the volume:
  ```bash
  docker volume rm repo_mysql-data
  ```
- Ensure your Spring Boot app is packaged as a JAR file (e.g., `your-app.jar`) in the `target/` directory after building with Maven or Gradle.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request on [GitHub](https://github.com/your-username/your-repo).