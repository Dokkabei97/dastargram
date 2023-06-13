FROM openjdk:17-alpine

RUN apt-get update

WORKDIR /home/d1t/app

COPY build/libs/dastargram-*.jar .

EXPOSE 8080

CMD ["java", "jar", "dastargram-*.jar"]
