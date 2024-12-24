FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn 
COPY mvnw .

COPY pom.xml .
COPY src ./src

ENTRYPOINT ["java", "-jar", "app.jar"]
