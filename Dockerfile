# syntax=docker/dockerfile:1
# Spring Boot 3.2.0 / Java 17 (artifactId=board) — multi-stage build
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
# 의존성: buildkit cache mount(.m2) → buildkit-cache PVC 에 저장·재사용(같은 버전 재다운로드 방지)
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -B -DskipTests dependency:go-offline
COPY src ./src
# spring-boot-maven-plugin(pom 선언)이 package 단계에서 실행가능 jar 로 repackage
RUN --mount=type=cache,target=/root/.m2 mvn -q -B -DskipTests clean package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/board-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
