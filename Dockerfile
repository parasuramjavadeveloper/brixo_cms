FROM openjdk:8
EXPOSE 8055
ADD target/brixo-0.0.1-SNAPSHOT.jar  brixo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","brixo-0.0.1-SNAPSHOT.jar"]
