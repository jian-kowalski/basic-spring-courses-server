FROM arm64v8/maven:3.8.5-amazoncorretto-17 as build
RUN mkdir /src
COPY . /src
WORKDIR /src
RUN mvn clean install  -DskipTests

FROM  arm64v8/amazoncorretto:17-alpine
RUN mkdir /app
COPY --from=build /src/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]