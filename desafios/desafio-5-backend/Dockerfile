FROM openjdk:17-alpine

LABEL maintainer="marcelomiguelpireslopes@gmail.com"

RUN mkdir /app

WORKDIR /app

COPY target/desafio-5-0.0.1-SNAPSHOT.jar /app/desafio-5.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/desafio-5.jar"]

