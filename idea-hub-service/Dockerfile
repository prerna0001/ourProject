FROM openjdk:11
WORKDIR /usr/src

ADD ./target/idea-hub-service-0.0.1-SNAPSHOT.jar /usr/src/idea-hub-service-0.0.1-SNAPSHOT.jar


ADD ./src/main/resources/Seed_Ideas1.xlsx /usr/src/Seed_Ideas1.xlsx
ENTRYPOINT ["java","-jar", "idea-hub-service-0.0.1-SNAPSHOT.jar"]