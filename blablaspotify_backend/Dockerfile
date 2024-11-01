FROM quay.balgroupit.com/library/openjdk:21-jre
RUN mkdir /opt/app/
ADD target/*.jar /opt/app/app.jar
ENV TZ=Europe/Berlin

ENV SRF_CONSUMER_KEY=ydOJ9Tl5y7oG10Y6J8OGnSvV64CdXeZP
ENV SRF_CONSUMER_SECRET=xnWQ6LXA8NtudpQs
ENV SPOTIFY_CLIENT_ID=d524fc41ac744eb6878f3a1d29c71f70
ENV SPOTIFY_CLIENT_SECRET=4c47e069aaed46cdb10f875d2e3dabc5

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
