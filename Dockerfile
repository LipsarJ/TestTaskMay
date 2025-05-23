FROM openjdk:17

WORKDIR /app

COPY ./build/libs/testTask-0.0.1-SNAPSHOT.jar /app/testTask.jar

EXPOSE 8080

CMD ["java", "-jar", "testTask.jar"]