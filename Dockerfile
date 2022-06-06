FROM maven:3.8.5-openjdk-17-slim as build
RUN mkdir /src
COPY . /src
WORKDIR /src
RUN mvn clean install  -DskipTests

FROM  openjdk:17-alpine
RUN mkdir /app
COPY --from=build /src/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]