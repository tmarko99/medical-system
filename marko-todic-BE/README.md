
# Spring Boot, MySQL, Spring Security, JWT, JPA, Rest API

Restful CRUD API for a medical clinic using Spring Boot, Mysql, JPA and Hibernate.

## Steps to Setup

**1. Clone the application**

```bash
git clone git@bitbucket.org:markot9/marko-todic.git
```

**2. Create Mysql database**
```bash
create database medical_system
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>