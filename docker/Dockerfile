FROM openjdk:8-jdk

ENV DB_HOST 127.0.0.1

ARG JAR_FILE

VOLUME /tmp

RUN mkdir gwf

COPY ${JAR_FILE} gwf/app.jar

ENTRYPOINT ["/bin/bash", "-c", "java -Dspring.data.mongodb.uri=mongodb://$DB_HOST/GalaxyWeatherForecaster -Djava.security.egd=file:/dev/./urandom -jar /gwf/app.jar"]