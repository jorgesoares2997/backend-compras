# Etapa 1: Construir a aplicação com Maven e OpenJDK 23
FROM maven:3.9.6-eclipse-temurin-17-slim AS build

# Instalar OpenJDK 23
RUN apt-get update && apt-get install -y openjdk-23-jdk

WORKDIR /app

# Copiar os arquivos do projeto para o contêiner
COPY . .

# Rodar o build Maven dentro do contêiner com OpenJDK 23
RUN mvn clean install -DskipTests

# Etapa 2: Rodar a aplicação com OpenJDK 23
FROM openjdk:23-slim

WORKDIR /app

# Copiar o artefato (JAR) gerado pela etapa de build
COPY --from=build /app/target/crud-spring-0.0.1-SNAPSHOT.jar app.jar

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
