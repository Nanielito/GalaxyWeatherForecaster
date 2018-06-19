#!/bin/bash

JAR_FILE='com.nanielito.galaxy.weather.forecaster-0.1.0-SNAPSHOT.jar'

cd ..

cp ../build/libs/*.jar .

docker build --build-arg JAR_FILE=$JAR_FILE -t gwf/gwf-app:latest .

rm *.jar