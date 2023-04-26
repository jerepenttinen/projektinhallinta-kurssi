FROM maven:3.9-eclipse-temurin-17 as build
WORKDIR api

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
COPY frontend frontend

RUN mvn clean install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/api/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.tamkstudents.cookbook.CookbookApplication"]