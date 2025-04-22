#!/bin/sh

echo "Erzeuge Runtime-Konfiguration..."

cat <<EOF > /tmp/env.js
window.__env = {
  API_URL: "$API_URL"
};
EOF

cp /tmp/env.js /usr/share/nginx/html/assets/env.js

echo "Starte Nginx..."
exec nginx -g 'daemon off;'
