# ------------------------------------------
# Docker file template for OpenApis
# Base template
#
# version 1.0
# ------------------------------------------
FROM openjdk:8-jre-alpine

# Add Maintainer Info
LABEL maintainer="vcalvoma"

#Environments
ENV APP_DIR /opt/app
ENV APP_NAME demo-deep-2.jar
ENV DEBUG=false
ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENV BOOTAPP_OPTS=""

RUN addgroup java && \
    adduser -D -G java java && \
    mkdir -p ${APP_DIR} && \
    chown java:java ${APP_DIR} 

WORKDIR ${APP_DIR}

ARG JAR_FILE=target/demo-deep-2.jar

# Copy files
COPY ${JAR_FILE} ${APP_NAME}
COPY template/entrypoint.sh .

RUN chmod 555 ${APP_NAME} && \
    chmod 755 entrypoint.sh && \
    touch ${APP_NAME} && \
    ls -lR

USER java

WORKDIR ${APP_DIR}

# start tomcat7
EXPOSE 8080

ENTRYPOINT ["sh", "/opt/app/entrypoint.sh"]
