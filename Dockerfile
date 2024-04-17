FROM openjdk:17-alpine
ADD target/*.jar app.jar

ENV DB_HOST=localhost

ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8081,suspend=n"

EXPOSE 8081 8081

ENTRYPOINT [ "sh", "-c", "java \
    -jar /app.jar \
    --cloud.db-host=$DB_HOST \
    " ]

