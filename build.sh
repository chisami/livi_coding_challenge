#!/bin/bash

### gradle build
### must install java and gradle
./gradlew clean build -x test

### build docker
BUILD_PATH="build/libs"
BUILD_FILE="livi-coding-challenge-0.0.1-SNAPSHOT.jar"
IMG_NAME="livi-coding-challenge"
DOCKREG_HOST="livi"

docker build \
  --force-rm --no-cache \
  --build-arg BUILD_PATH=${BUILD_PATH} \
  --build-arg BUILD_FILE=${BUILD_FILE} \
  --tag ${DOCKREG_HOST}/${IMG_NAME}:latest .
