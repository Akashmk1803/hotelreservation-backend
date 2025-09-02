# Use Java 21 base image
FROM eclipse-temurin:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy project files
COPY . .

# Build the application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the JAR
CMD ["java", "-jar", "target/*.jar"]
