FROM maven:3-openjdk-17-slim

WORKDIR /usr/src/app

# Copy pom first so that dependencies are cache
COPY pom.xml /usr/src/app/pom.xml
RUN mvn dependency:go-offline

# Package the app
COPY src/ /usr/src/app/src
RUN mvn package

ENV PORT 8085
EXPOSE $PORT

CMD [ "sh", "-c", "mvn -Dserver.port=${PORT} spring-boot:run" ]