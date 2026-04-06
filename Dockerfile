# 1. Phase de Build : on compile le projet
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests [cite: 46, 47, 56]

# 2. Phase de Run : on ne garde que le strict nécessaire
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# On récupère le fichier JAR généré à l'étape précédente
COPY --from=build /app/target/*.jar app.jar [cite: 57]
EXPOSE 8080 [cite: 50, 58]
ENTRYPOINT ["java", "-jar", "app.jar"] [cite: 49]