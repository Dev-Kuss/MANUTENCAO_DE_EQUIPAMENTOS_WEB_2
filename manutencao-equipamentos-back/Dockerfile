# Etapa de build usando Maven e JDK 21 da distribuição Eclipse Temurin
FROM maven:3.9.4-eclipse-temurin-21 as builder
WORKDIR /app

# Copiar apenas o pom.xml primeiro
COPY pom.xml .

# Baixar dependências
RUN mvn dependency:go-offline

# Não precisamos copiar o código fonte aqui
# COPY . .

EXPOSE 8080
EXPOSE 5005

# Modificar para usar spring-boot:run em vez de jar
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005'"]
