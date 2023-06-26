FROM openjdk:17-alpine

RUN mkdir /app

WORKDIR /app

COPY target/desafio-5-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
