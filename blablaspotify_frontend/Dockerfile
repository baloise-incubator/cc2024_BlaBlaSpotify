FROM quay.balgroupit.com/library/nginx:1.23-alpine

COPY --chown=nginx:nginx /dist/blablaspotify_frontend/browser/ /usr/share/nginx/html/

COPY default.conf /etc/nginx/conf.d/default.conf

COPY ./entrypoint.sh /entrypoint.sh

USER root
RUN chmod +x /entrypoint.sh
USER nginx

ENTRYPOINT ["/entrypoint.sh"]
