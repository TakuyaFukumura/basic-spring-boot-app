# Basic Spring Boot Application

Basic Spring Boot application serving as a foundation for Spring Boot development. This is a web application using Spring Boot 3.5.4, Java 17, Maven, Thymeleaf template engine, Spring Data JPA with H2 in-memory database, and Spring Boot Actuator for monitoring.

Always reference these instructions first and fallback to search or bash commands only when you encounter unexpected information that does not match the info here.

## Working Effectively

### Bootstrap and Build
- Install Java 17+ (OpenJDK recommended) before starting.
- Build the application:
  - `./mvnw clean package` -- Initial build takes 4-5 minutes with dependency downloads. NEVER CANCEL. Set timeout to 600+ seconds.
  - Subsequent builds take ~5 seconds with cached dependencies.
- Run tests:
  - `./mvnw test` -- takes 2-4 seconds. NEVER CANCEL (though it's fast). Set timeout to 60+ seconds for safety.

### Run the Application
- **ALWAYS build first** before running the application.
- Development mode: `./mvnw spring-boot:run` -- starts in ~4 seconds. NEVER CANCEL during startup.
- Production mode: `java -jar target/myproject.jar` -- starts in ~5-6 seconds after successful build.
- Application runs on http://localhost:8080

### Docker (Note: May not work in restricted environments)
- Docker build: `docker compose build` -- may fail due to SSL certificate issues in sandbox environments. Document as "Docker build fails due to SSL certificate restrictions in this environment."
- Docker run: `docker compose up` -- use only if build succeeds.

## Validation

### Manual Testing Requirements
- **ALWAYS validate functionality** after making code changes:
  1. Build and run the application using one of the run commands above
  2. Test the homepage: `curl -s http://localhost:8080 | grep -i "hello"` should return `<span>Hello World!</span>`
  3. Test health endpoint: `curl -s http://localhost:8080/actuator/health` should return `{"status":"UP"}`
  4. Verify H2 console is accessible at http://localhost:8080/h2-console (browser-based)
  5. Stop the application (Ctrl+C) before proceeding

### Test Scenarios to Always Run
- **Database integration**: The application uses H2 in-memory database with schema.sql and data.sql initialization. Always verify the "Hello World!" message appears on the homepage, confirming database connectivity.
- **Web layer**: Test that the Thymeleaf template rendering works by checking the complete HTML response includes Bootstrap CSS and the message display.
- **Spring Boot features**: Verify Actuator health checks work and H2 console is accessible for development debugging.

## Common Tasks

### File Structure Overview
```
src/
├── main/
│   ├── java/com/example/myapplication/
│   │   ├── MyApplication.java          # Main Spring Boot application class
│   │   ├── controller/IndexController.java  # Web controller handling requests
│   │   ├── service/IndexService.java   # Business logic service layer
│   │   ├── entity/Message.java         # JPA entity for database
│   │   └── repository/MessageRepository.java  # Data access layer
│   └── resources/
│       ├── application.properties      # Main configuration
│       ├── schema.sql                 # Database schema initialization
│       ├── data.sql                   # Database data initialization  
│       └── templates/index.html       # Thymeleaf template
└── test/
    └── groovy/com/example/myapplication/  # Spock tests written in Groovy
```

### Key Configuration Files
- **pom.xml**: Maven project configuration with Spring Boot 3.5.4, Java 17, and Spock testing framework
- **application.properties**: H2 database configuration, JPA settings, and Actuator endpoints
- **Dockerfile**: Multi-stage Docker build (may not work in restricted environments)
- **docker-compose.yml**: Docker Compose configuration with development profile

### Development Workflow
- Always use `./mvnw` (Maven wrapper) instead of `mvn` to ensure consistent Maven version
- The application uses Lombok - ensure your IDE has Lombok support for proper code completion
- Tests are written in Groovy using Spock framework - more expressive than JUnit
- The app initializes with a single "Hello World!" message in the database on every startup

### Build and CI Information
- CI builds run: `mvn clean package` (same as local development)
- No additional linting tools configured - rely on Maven compiler warnings
- GitHub Actions workflow in `.github/workflows/build.yml` builds the project on every push
- Build artifacts: `target/myproject.jar` (Spring Boot fat JAR, ~60MB)

### Troubleshooting
- If Maven build fails with dependency resolution: Delete `~/.m2/repository` and retry build
- If application fails to start: Check port 8080 is available with `lsof -i :8080` 
- If tests fail: Run `./mvnw clean test` to ensure clean state
- If Docker build fails: Use traditional Maven approach instead, Docker may not work in restricted environments