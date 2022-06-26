FROM arm64v8/maven as build
RUN mkdir /src
COPY . /src
WORKDIR /src
RUN mvn clean install -DspkipTests

FROM amazoncorretto:17.0.3
ENV SPRING_PROFILES=default
EXPOSE 5000 9000
RUN mkdir /app
COPY --from=build /src/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]