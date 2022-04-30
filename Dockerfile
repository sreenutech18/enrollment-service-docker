# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Make port  available to the world outside this container
EXPOSE 9092

COPY target/enrollment-service-1.0-SNAPSHOT.jar enrollment-service-1.0-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","enrollment-service-1.0-SNAPSHOT.jar"]