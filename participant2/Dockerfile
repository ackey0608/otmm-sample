FROM openjdk:17-alpine

WORKDIR /app-build
ADD . .
RUN mkdir /app \
&& sh ./gradlew bootJar --no-daemon \
&& cp ./build/libs/*.jar /app/

EXPOSE 8082
WORKDIR /app
ENTRYPOINT [ "java", "-jar", "participant2.jar" ]
