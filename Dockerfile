FROM openjdk:8
ADD target/CalculatorDevops-1.0-SNAPSHOT.jar  CalculatorDevops-1.0-SNAPSHOT.jar

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "CalculatorDevops-1.0-SNAPSHOT.jar", "org.pragati.Calculator" ]

