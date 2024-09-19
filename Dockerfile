FROM maven:3.8.3-openjdk-17 as maven_builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=maven_builder /app/target/*.jar ./application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
