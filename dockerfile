FROM openjdk:17

COPY ./target/crud-spring-0.0.1-SNAPSHOT.jar /app/crud-spring-0.0.1-SNAPSHOT.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "crud-spring-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080