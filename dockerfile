# Usando a imagem oficial do Java
FROM openjdk:17-jdk-slim

# Definir diretório de trabalho
WORKDIR /app

# Copiar os arquivos do projeto para o contêiner
COPY . .

# Construir o projeto (caso esteja usando Maven)
RUN ./mvnw clean install -DskipTests

# Expor a porta da aplicação
EXPOSE 8080

# Rodar a aplicação
CMD ["java", "-jar", "target/crud-spring-0.0.1-SNAPSHOT.jar"]