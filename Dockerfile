FROM openjdk:8-jdk-alpine

ARG BUILD_PATH
ARG BUILD_FILE

COPY ${BUILD_PATH}/${BUILD_FILE} app.jar

ENV JAVA_OPTS="-Xmx750m -Djava.security.egd=file:/dev/urandom"

HEALTHCHECK --interval=30s --timeout=3s --start-period=1200s --retries=4 \
  CMD bash -c 'echo > /dev/tcp/localhost/8089 || exit 1'

ENTRYPOINT ["java","-jar","/app.jar"]
