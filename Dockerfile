FROM openjdk:8-jdk-alpine
EXPOSE 8086

ADD target/*.jar /
ENTRYPOINT ["java", "-jar", "Timesheet-spring-boot-core-data-jpa-mvc-REST-1-5.0.war","--spring.config.name=prod"]

