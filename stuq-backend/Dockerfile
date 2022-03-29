FROM maven:3-openjdk-17-slim

WORKDIR /usr/src/app

COPY . /usr/src/app
RUN mvn package

ENV PORT 8085
EXPOSE $PORT

CMD [ "sh", "-c", "mvn -Dserver.port=${PORT} spring-boot:run" ]