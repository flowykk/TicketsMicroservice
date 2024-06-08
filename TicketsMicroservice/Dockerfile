FROM amazoncorretto:21-alpine3.19

WORKDIR /app

ADD build/libs/kotlin-template-1.0.0-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]