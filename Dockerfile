FROM openjdk:17-jdk-slim

# Environment variable for custom Java options (optional)
ENV JAVA_OPTS=""

# Volume for Spring Boot temporary files
VOLUME /tmp

# Set argument to define where the JAR file is located
ARG JAR_FILE=target/Task_Management-0.0.1-SNAPSHOT.jar

# Copy the JAR into the container
COPY ${JAR_FILE} app.jar

# Set entrypoint with proper variable interpolation
ENTRYPOINT ["java", "-jar", "/app.jar"]

