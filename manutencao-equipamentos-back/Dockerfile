# Etapa de build usando Maven e JDK 21 da distribuição Eclipse Temurin
FROM maven:3.9.4-eclipse-temurin-21 as builder
WORKDIR /app

COPY .. .

RUN mvn clean install -DskipTests

# Etapa de runtime usando Eclipse Temurin JDK 21
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
EXPOSE 5005

# Ajustando o ENTRYPOINT para incluir as opções de debug
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
