# Usar uma imagem base do Java 23 para a build
FROM eclipse-temurin:23-jdk-alpine as build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml e o script Maven Wrapper para o container
COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn

# Baixar as dependências do Maven
RUN ./mvnw dependency:go-offline -B

# Copiar o código-fonte do projeto para o container
COPY src ./src

# Compilar o projeto (sem testes para acelerar o processo)
RUN ./mvnw package -DskipTests

# Usar uma imagem base mais leve do Java 23 Runtime
FROM eclipse-temurin:23-jre-alpine

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR compilado da fase de build
COPY --from=build /app/target/*.jar app.jar

# Expor a porta 8080
EXPOSE 8080

# Comando para executar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
