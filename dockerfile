# Etapa 1: Construir a aplicação com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar os arquivos do projeto para o contêiner
COPY . .

# Rodar o build Maven dentro do contêiner
RUN mvn clean package -DskipTests

# Etapa 2: Rodar a aplicação com OpenJDK
FROM openjdk:17-slim

WORKDIR /app

# Copiar o artefato (JAR) gerado pela etapa de build
COPY --from=build /app/target/crud-spring-0.0.1-SNAPSHOT.jar app.jar

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
