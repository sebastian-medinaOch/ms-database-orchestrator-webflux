FROM openjdk:21-jdk-slim
WORKDIR /app
ENV URL_BASE_PATH=http://simulado:80
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]