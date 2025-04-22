FROM ubuntu/jre:21
RUN mkdir /opt/app/
ADD target/*.jar /opt/app/app.jar
ENV TZ=Europe/Berlin

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
