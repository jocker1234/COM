FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN mkdir -p /app/
RUN chmod 777 /app/
RUN mkdir -p /app/logs/
ADD backend/target/backend-1.0.0.war /app/app.war
ENTRYPOINT ["java", "-jar", "/backend/src/resources/lib/fakeSMTP-2.0.jar", "-s -b -p 25"]
ENTRYPOINT ["java", "-jar", "/app/app.war"]
