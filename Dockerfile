FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR application
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
RUN ls -l target
COPY target/brain-0.0.1-SNAPSHOT.jar application.jar
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract


FROM eclipse-temurin:17-jre-alpine
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]