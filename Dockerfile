FROM adoptopenjdk:11-jre-hotspot
WORKDIR /usr/src/app
COPY target/*-runner.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
