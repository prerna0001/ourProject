FROM  openjdk:11-jdk-stretch
WORKDIR /i2ps/user

ADD target/user-login-service-0.0.1-SNAPSHOT.jar /i2ps/user/user-login-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT  ["java","-jar","user-login-service-0.0.1-SNAPSHOT.jar"]