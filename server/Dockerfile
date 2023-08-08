FROM openjdk:17-alpine
ENV APP_DIR=/home
EXPOSE 8080
RUN apk add --no-cache wget
RUN wget -O elastic-apm-agent.jar 'https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=co.elastic.apm&a=elastic-apm-agent&v=LATEST'
COPY target/basic-spring-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-javaagent:/elastic-apm-agent.jar", "-jar", "app.jar"]