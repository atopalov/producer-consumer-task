FROM openjdk:17-jdk-slim
WORKDIR ./app
COPY . /app
#RUN ./gradlew build --no-daemon
COPY src/main/resources/application.properties application.properties
#EXPOSE 8082
ENTRYPOINT ["java","-jar","build/libs/consumer-1.0.jar"]