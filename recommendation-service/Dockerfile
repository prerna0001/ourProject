FROM openjdk:11-jdk-stretch

WORKDIR /src/main/i2ps

ADD ./target/recommendation-service-0.0.1-SNAPSHOT.jar   /src/main/i2ps/recommendation-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","recommendation-service-0.0.1-SNAPSHOT.jar"]