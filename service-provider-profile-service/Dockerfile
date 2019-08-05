FROM  openjdk:11-jdk-stretch
WORKDIR /i2ps/user

ADD target/service-provider-profile-service-0.0.1-SNAPSHOT.jar /i2ps/user/service-provider-profile-service-0.0.1-SNAPSHOT.jar
ADD ./src/main/resources/Seed_Ideas2.xlsx /i2ps/user/Seed_Ideas2.xlsx
ENTRYPOINT  ["java","-jar","service-provider-profile-service-0.0.1-SNAPSHOT.jar"]