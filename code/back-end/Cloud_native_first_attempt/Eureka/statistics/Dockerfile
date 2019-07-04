FROM openjdk:8-jdk-alpine
EXPOSE 8762
VOLUME /tmp
ADD s_login.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
