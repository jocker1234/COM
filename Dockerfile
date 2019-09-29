FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN mkdir -p /app/
RUN chmod 777 /app/
RUN mkdir -p /app/logs/
COPY init.sql /docker-entrypoint-initdb.d
ADD backend/target/backend-1.0.0.war /app/app.war
ENTRYPOINT ["java", "-jar", "/app/app.war"]
