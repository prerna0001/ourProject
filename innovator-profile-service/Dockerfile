FROM  openjdk:11-jdk-stretch

ADD  target/innovator-profile-service-0.0.1-SNAPSHOT.jar i2ps/innovator/innovator-profile-service-0.0.1-SNAPSHOT.jar

WORKDIR /i2ps/innovator

ENTRYPOINT  ["java","-jar","innovator-profile-service-0.0.1-SNAPSHOT.jar"]