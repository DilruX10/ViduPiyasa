# Specify the base image
FROM adoptopenjdk/openjdk11:alpine-jre


# Copy the jar files to the container
COPY target/*.jar courseservice.jar


# Expose the port that the microservice will listen on
EXPOSE 3003

# Start the microservice
ENTRYPOINT ["java","-jar","/courseservice.jar"]
