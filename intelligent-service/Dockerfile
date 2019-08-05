FROM  openjdk:11-jdk-stretch
WORKDIR /i2ps/intelligent-service

ADD target/intelligent-service-0.0.1-SNAPSHOT.jar /i2ps/intelligent-service/intelligent-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT  ["java","-jar","intelligent-service-0.0.1-SNAPSHOT.jar"]