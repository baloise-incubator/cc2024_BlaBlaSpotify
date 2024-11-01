FROM quay.balgroupit.com/library/openjdk:21-jre
RUN mkdir /opt/app/
ADD target/*.jar /opt/app/app.jar
ENV TZ=Europe/Berlin

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
