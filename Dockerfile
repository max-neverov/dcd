FROM openjdk:8u131-jdk-alpine

WORKDIR /opt/dcd
ADD build/libs/dcd.jar /opt/dcd/dcd.jar

RUN apk add --no-cache coreutils

EXPOSE 8080

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar dcd.jar
