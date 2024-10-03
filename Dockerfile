# Usar o Java JDK como base para build
FROM eclipse-temurin:23-jdk-alpine AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo mvnw e configurar permissões
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./

# Conceder permissão de execução para mvnw
RUN chmod +x ./mvnw

# Rodar o Maven para baixar as dependências
RUN ./mvnw dependency:go-offline -B

# Copiar o código-fonte do projeto
COPY src ./src

# Rodar o Maven para empacotar o aplicativo
RUN ./mvnw package -DskipTests

# Usar o Java JRE como base para rodar o app
FROM eclipse-temurin:23-jre-alpine

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o jar gerado na fase de build
COPY --from=build /app/target/me-0.0.1-SNAPSHOT.jar ./app.jar

# Comando para rodar o jar
ENTRYPOINT ["java", "-jar", "app.jar"]
