# Start with a base image containing Java runtime
FROM openjdk:8
# Add Maintainer Info
LABEL maintainer="iiitb"
# Add a volume pointing to /tmp to make database persistent
VOLUME /tmp
# Make port 8082 available to the world outside this container
EXPOSE 8082
# Add the application's jar to the container
ADD /target/cms.war cms.war
# Run the war file
ENTRYPOINT ["java","-jar","cms.war"]