ToDo CRUD (Java, Spring Boot, Postgres, Maven)

A simple REST API to manage todo items, built with Java and Spring Boot, using PostgreSQL for persistence and Maven for build.
Features

    CRUD endpoints for todos (create, read, update, delete).

Layered structure: Controller → Service → Repository (Spring Data JPA).

Local development with PostgreSQL and Maven build workflow.
Requirements

    Java 17+.

Maven 3.8+.

PostgreSQL 13+ (local or Docker).
Getting Started
1) Clone and build

   git clone https://github.com/Mukul141/todo-crud && cd todo-crud.

mvn clean package to build the application jar.
2) Start PostgreSQL

Option A: Docker

    docker run --name todo-db -e POSTGRES_USER=todo -e POSTGRES_PASSWORD=todo -e POSTGRES_DB=todos -p 5432:5432 -d postgres:16.

Option B: Local install

    Create database todos and a user todo with password todo; grant privileges.

3) Configure application

Set database connection in src/main/resources/application.yaml or application.properties:

    spring.datasource.url=jdbc:postgresql://localhost:5432/todos.

spring.datasource.username=todo.

spring.datasource.password=todo.

spring.jpa.hibernate.ddl-auto=update (use validate in prod).
4) Run the app

   mvn spring-boot:run or java -jar target/todo-crud-*.jar.

Health check: GET http://localhost:8080/actuator/health (if Actuator is enabled).
API

Base URL

    http://localhost:8080.

Endpoints

    POST /api/todos — create a todo (JSON body with fields like task, done, completeDate).

GET /api/todos — list all todos.

GET /api/todos/{id} — get a todo by id.

PUT /api/todos/{id} — update a todo.

DELETE /api/todos/{id} — delete a todo by id.

Examples

    Create:
    curl -X POST http://localhost:8080/api/todos -H "Content-Type: application/json" -d '{"task":"Read a book","done":false}'
    .

List:
curl http://localhost:8080/api/todos.
Project Structure

    src/main/java/.../controller — REST controllers.

src/main/java/.../service — business logic.

src/main/java/.../repository — Spring Data JPA repositories.

src/main/java/.../model — JPA entities.

src/main/resources — configuration files.
Configuration Tips

    Use environment variables for DB credentials in non-dev environments.

Prefer spring.jpa.hibernate.ddl-auto=validate in production to avoid accidental schema changes.
Build and Test

    Build: mvn clean package.

Run tests: mvn test.

Common plugins and setup are available in Spring Boot + PostgreSQL Maven examples for reference.
Contributing

    Open issues and pull requests with clear descriptions and minimal repro steps are welcome.

License

    No license specified yet. All rights reserved by default until a LICENSE is added.
