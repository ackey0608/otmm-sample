FROM openjdk:17-alpine

WORKDIR /app-build
ADD . .
RUN mkdir /app \
&& sh ./gradlew bootJar --no-daemon \
&& cp ./build/libs/*.jar /app/

EXPOSE 8081
WORKDIR /app
ENTRYPOINT [ "java", "-jar", "participant1.jar" ]
