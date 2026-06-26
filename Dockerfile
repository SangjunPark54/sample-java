# syntax=docker/dockerfile:1
# Spring Boot 3.2.0 / Java 17 (artifactId=board) — multi-stage build
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -B -DskipTests dependency:go-offline
COPY src ./src
# 패키징 후 spring-boot repackage 로 실행 가능한 fat jar 생성(pom 에 plugin 미선언 대비, parent 버전 고정)
RUN mvn -q -B -DskipTests clean package \
 && mvn -q -B org.springframework.boot:spring-boot-maven-plugin:3.2.0:repackage

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/board-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
