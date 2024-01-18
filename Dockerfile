FROM adoptopenjdk:11-jre-hotspot
WORKDIR /usr/src/app
COPY target/*-runner.jar app.jar
EXPOSE 8080
RUN ./mvnw package -DskipTests
CMD ["java", "-jar", "app.jar"]
