FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn 
COPY mvnw .

COPY pom.xml .
COPY src ./src

RUN ./mvnw package -DskipTests

ENTRYPOINT ["java", "-jar", "app.jar"]
