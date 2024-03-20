FROM amazoncorretto:17-alpine-jdk
ADD target/hackaton-worktime-management-0.0.1.jar hackaton-worktime-management.jar
ENTRYPOINT ["java", "-jar", "hackaton-worktime-management.jar"]
EXPOSE 8080