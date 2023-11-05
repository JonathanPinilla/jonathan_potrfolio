# Portfolio Project

This project serves as the backend for my personal portfolio, where I showcase my projects, experiences, and studies.

### Tools Used

The project is developed using the following tools and technologies:

* **Java:** The primary programming language.
* **Spring Framework with Spring Boot:** The main framework used for building the application.
* **Maven:** Used as the package manager for managing project dependencies.
* **MongoDB:** Employed as the database management system.
* **IntelliJ Idea**: Used as the IDE.

Getting Started
To run this project, follow these steps:

Clone the project using Git:

`git clone https://github.com/JonathanPinilla/jonathan_portfolio`

Synchronize project dependencies with Maven:
`mvn clean verify`

Create an application.properties file with the following structure:

```
# Replace 'your_mongo_uri_here' with your MongoDB Atlas URI or local URI
spring.data.mongodb.uri=your_mongo_uri_here
```

You can run the project using Maven:
`mvn spring-boot:run`

Or alternatively, by running the JAR file:
`java -jar target/your_project_jar_file.jar`.

**(*Make sure your Java JDK is at least version 17*)**

## Entity Diagram
The following entity diagram illustrates the entities that will be used in the project:

![Entities diagram](/assets/images/portfolio_entities.jpg)