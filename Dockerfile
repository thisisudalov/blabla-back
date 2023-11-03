FROM openjdk:21-jdk
EXPOSE 8080
ARG JAR_FILE=target/blabla-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]