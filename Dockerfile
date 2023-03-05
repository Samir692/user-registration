FROM openjdk:17-alpine

COPY target/user-registration-system-0.0.1-SNAPSHOT.jar user-registration-system-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "user-registration-system-0.0.1-SNAPSHOT.jar"]
