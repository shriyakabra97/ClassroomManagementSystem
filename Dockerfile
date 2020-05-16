FROM openjdk:8
ADD target/ClassroomManagementSystem-0.0.1-SNAPSHOT.jar ClassroomManagementSystem-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "ClassroomManagementSystem-0.0.1-SNAPSHOT.jar"]