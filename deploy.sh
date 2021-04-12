#!/bin/bash

### deploy docker
IMG_NAME="livi-coding-challenge"
DOCKREG_HOST="livi"

docker run \
  --detach=true \
  --name=${IMG_NAME} \
  -p 8089:8089 \
  -e SPRING_PROFILES_ACTIVE=local \
  ${DOCKREG_HOST}/${IMG_NAME}:latest
