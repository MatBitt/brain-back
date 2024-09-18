FROM maven:3.8.3-openjdk-17 as maven_builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install
RUN mv target/*.jar target/application.jar
RUN ls -l /app/target

FROM openjdk:17-jdk-alpine as builder
WORKDIR /app
COPY --from=maven_builder /app/target/application.jar ./
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]