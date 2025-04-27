FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine     
VOLUME /tmp
COPY --from=build /app/target/Gateway.jar Gateway.jar
ENTRYPOINT [ "java","-jar","/Gateway.jar" ]