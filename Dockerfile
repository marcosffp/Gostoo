# Etapa de build: Usar uma imagem com o JDK para compilar o projeto
FROM eclipse-temurin:17-jdk AS builder

# Definir o diretório de trabalho
WORKDIR /app

# Copiar apenas os arquivos do projeto que são necessários para o build
# Inclui o pom.xml e os arquivos de código fonte
COPY src/back /app

# Copiar o arquivo mvnw (Maven Wrapper) e garantir permissão de execução
COPY mvnw /app
COPY .mvn /app/.mvn
RUN chmod +x mvnw

# Construir o projeto usando Maven, sem rodar os testes
RUN ./mvnw clean package -DskipTests

# Etapa final: Usar uma imagem com o JRE para rodar a aplicação
FROM eclipse-temurin:17-jre

# Definir o diretório de trabalho para a aplicação
WORKDIR /app

# Copiar o JAR gerado na etapa de build para o diretório final
COPY --from=builder /app/target/*.jar app.jar

# Copiar os arquivos estáticos (imagens) para o diretório correto dentro do container
COPY src/back/src/main/resources/static/image /app/src/main/resources/static/image

# Garantir que as imagens tenham permissões adequadas
RUN chmod -R 755 /app/src/main/resources/static/image



# Expor a porta padrão do Spring Boot (8080)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]