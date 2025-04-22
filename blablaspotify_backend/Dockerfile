FROM optimoz/openjre-21.0.3:0.4
RUN mkdir /opt/app/
ADD target/*.jar /opt/app/app.jar
ENV TZ=Europe/Berlin

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
