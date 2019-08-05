FROM  openjdk:11-jdk-stretch

ADD  target/innovator-management-service-0.0.1-SNAPSHOT.jar i2ps/innovator-management/innovator-management-service-0.0.1-SNAPSHOT.jar

WORKDIR /i2ps/innovator-management

ENTRYPOINT  ["java","-jar","innovator-management-service-0.0.1-SNAPSHOT.jar"]