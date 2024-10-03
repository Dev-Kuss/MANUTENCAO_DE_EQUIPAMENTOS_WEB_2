# Use a imagem base do JDK para compilar a aplicação
FROM eclipse-temurin:23-jdk-alpine AS build

# Instala o Maven no container
RUN apk add --no-cache maven

WORKDIR /app

# Copie o arquivo pom.xml e baixe as dependências do Maven
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copie o código do projeto
COPY . .

# Compile a aplicação
RUN mvn clean package -DskipTests

# Use uma imagem mais leve para rodar a aplicação
FROM eclipse-temurin:23-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar ./app.jar

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
