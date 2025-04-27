FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine     
VOLUME /tmp
COPY --from=build /app/target/Order.jar Order.jar
ENTRYPOINT [ "java","-jar","/Order.jar" ]