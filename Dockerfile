# Start with a base image containing Java runtime
FROM openjdk:8
# Add Maintainer Info
LABEL maintainer="github.com/mukeshpilaniya"
# Add a volume pointing to /tmp to make database persistent
VOLUME /tmp
# Make port 8082 available to the world outside this container
EXPOSE 8082
# Add the application's jar to the container
ADD /target/cms-springboot.jar cms-springboot.jar
# Run the jar file
ENTRYPOINT ["java","-jar","cms-springboot.jar"]