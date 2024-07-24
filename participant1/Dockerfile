FROM openjdk:17-alpine

RUN ./gradlew bootJar --no-daemon

RUN mkdir /app
COPY src/build/libs/*.jar /app/

EXPOSE 8081
WORKDIR /app
ENTRYPOINT [ "java", "-jar", "participant1.jar" ]
