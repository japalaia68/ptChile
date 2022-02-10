FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/ptChile-01.00.00-RC-01.jar ptChile-01.00.00-RC-01.jar
ENTRYPOINT ["java","-jar","/ptChile-01.00.00-RC-01.jar"]