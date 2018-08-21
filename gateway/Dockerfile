FROM openjdk:8

ARG BASE_NAME
ADD ./target/$BASE_NAME.jar /opt/
RUN chmod 755 /opt/$BASE_NAME.jar
RUN ln -s /opt/$BASE_NAME.jar /opt/service.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/service.jar $RUN_ARGS

HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:4000/actuator/health || exit 1

EXPOSE 8080
