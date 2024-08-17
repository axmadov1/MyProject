
FROM openjdk:11-jre-slim
VOLUME /tmp
COPY target/yourproject-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
