# Etapa 1: Construção do projeto usando Maven
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia os arquivos do projeto e compila
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagem para a aplicação final
FROM openjdk:17-slim
WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Variáveis de ambiente para conexão com o banco de dados
ENV DB_HOST=db.kphcxuvtgmgymnfodofw.supabase.co
ENV DB_PORT=5432
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=vymfej-zyrpyb-jaWcy6
ENV DB_NAME=postgres

# Baixando o script wait-for-it.sh para esperar a conexão do banco
RUN apt-get update && apt-get install -y curl && \
    curl -sLo /usr/local/bin/wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh && \
    chmod +x /usr/local/bin/wait-for-it.sh

# Porta exposta
EXPOSE 8080

# Aguardar o banco de dados estar disponível e então iniciar a aplicação com mensagem de sucesso
ENTRYPOINT ["/bin/sh", "-c", "/usr/local/bin/wait-for-it.sh ${DB_HOST}:${DB_PORT} -- echo 'Banco de dados acessado com sucesso!'; java -jar app.jar"]