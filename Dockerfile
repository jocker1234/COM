FROM openjdk:8-jdk-alpine
VOLUME /tmp
ENV PORT 8080
EXPOSE 8080
RUN mkdir -p /usr/src/backend/
RUN chmod 777 /usr/src/backend/
RUN mkdir -p /usr/logs
ADD backend/target/backend-1.0.0.war /usr/src/backend/app.war
CMD ["java", "-jar", "/usr/src/backend/src/resources/lib/fakeSMTP-2.0.jar", "-s -b -p 25"]
CMD ["java", "-jar", "/usr/src/backend/app.war"]

FROM node:12.2.0 as builder
RUN mkdir /usr/src/frontend
WORKDIR /usr/src/frontend
COPY frontend/src/package*.json /usr/src/frontend/
RUN npm install
COPY . /usr/src/frontend
RUN npm run build

FROM nginx:1.13.12-alpine
RUN rm -rf /usr/share/nginx/html/*
COPY /nginx/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /usr/src/frontend/public /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "dameon off;"]
