# Use Java 17 base image
FROM eclipse-temurin:17-jdk

# Set working directory inside container
WORKDIR /app

# Copy project files
COPY . .

# Make mvnw executable
RUN chmod +x mvnw

# Build the application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the JAR
CMD ["sh", "-c", "java -jar target/*.jar"]
