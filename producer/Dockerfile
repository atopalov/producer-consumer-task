FROM openjdk:17-jdk-slim
WORKDIR ./app
COPY . /app
#RUN ./gradlew build --no-daemon
COPY src/main/resources/application.properties application.properties
#EXPOSE 8081
ENTRYPOINT ["java","-jar","build/libs/producer-1.0.jar"]