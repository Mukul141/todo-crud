# --- Stage 1: Build the application ---
# Use a base image with Maven and JDK 17 to build our project
FROM maven:3.9.8-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml first to leverage Docker's layer caching.
# If pom.xml doesn't change, Docker won't re-download dependencies.
COPY pom.xml .

# Copy the rest of the source code
COPY src ./src

# Package the application, skipping the tests since they should be run in the CI pipeline
RUN mvn package -DskipTests


# --- Stage 2: Create the final, lean production image ---
# Use a lightweight JRE (Java Runtime Environment) image, not the full JDK
FROM eclipse-temurin:17-jre-focal

# Set the working directory
WORKDIR /app

# Copy the compiled .jar file from the 'build' stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# The command to run when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]