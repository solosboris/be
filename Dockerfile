FROM openjdk:19
MAINTAINER dedalus.com
COPY target/denom-0.0.1-SNAPSHOT.jar denom.jar
ENTRYPOINT ["java","-jar","/denom.jar"]