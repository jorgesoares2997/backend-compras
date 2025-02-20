# Escolhendo a imagem base do OpenJDK (Java 17, que você está usando)
FROM maven:3.9.9-eclipse-temurin-17 

# Definindo o diretório de trabalho dentro do container
WORKDIR /app

# Copiando os arquivos do seu projeto local para o container
# A pasta .m2 contém dependências Maven (se estiver usando Maven)
COPY pom.xml ./
COPY src ./src

# Se você usa Maven para build, faça o build do seu projeto no Docker
RUN mvn clean package -DskipTests

# Expondo a porta onde a API vai rodar
EXPOSE 8080

# Comando para rodar a aplicação (ajuste conforme necessário)
CMD ["java", "-jar", "target/crud-spring-0.0.1-SNAPSHOT.jar"]