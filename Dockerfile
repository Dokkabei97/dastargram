FROM openjdk:17-alpine

LABEL MAINTAINER="wkdrn970@naver.com"

ARG VERSION

WORKDIR /home/d1t/app

COPY build/libs/dastargram-${VERSION}.jar app.jar

EXPOSE 8080

CMD ["java", "jar", "app.jar -Dspring.profiles.active=prod"]
