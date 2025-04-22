# cc2024_BlaBlaSpotify
Code Camp - Web Radio with Spotify

# Problem
You want to hear the BlaBla from some radio station (e.g. Radio SRF 1, News, etc.), but you can't stand the music there.
You want both, good music AND blabla

# Solution
Mix the your favourite music stream together with the Blabla from a chosen radio station. And be happy slightly smiling face

# Approach to Solution
1. Consume Blabla-Stream (SRF 1)
1. Consume Music-Stream (Spotify) (API key)
1. Create one final stream, where User can switch between Blabla and Music
1. Automatic switching to Blabla-Stream, when blabla is broadcasted
1. Smooth Transitions (Wait with Blabla until song has ended, or Smooth Fade-out/in)
2. PowerPoint Slides
2. ----- up to here happened in the CodeCamp in Emmetten -----
1. Deployment to Incubator
2. Nice UI for Spotify (Playlists, Artists, Skip Button, Login etc.)
3. Display current song/Playlist/Blabla-Epiosode
1. Radio station selectable from 50'000 stations (-> not possible due to lack of meta data)
1. Go-Mobile: PWA
1. Android/iOS app
2. Mix Music from station 1 with Blabla from station 2
3. Mix Music from station 1 with Spotify during the Blabla episodes
4. Be millionaire and love-your-live!

# Try it out
<link here>

# Deploy it on incubator
## Backend
1. mvn clean install
1. docker build -t blabla-backend .
1. docker run -p 8080:8080 blabla-backend
1. docker logs -f blabla-backend
3. docker tag blabla-backend raffaelheinzer248/blabla-spotify-backend
4. docker push raffaelheinzer248/blabla-spotify-backend
2. docker rm -f blabla-backend
3. oc create secret generic blabla-secrets --namespace=blabla-spotify --dry-run=client -o yaml --from-literal="SRF_CONSUMER_KEY=FIXME" --from-literal="SRF_CONSUMER_SECRET=FIXME" --from-literal="SPOTIFY_CLIENT_ID=FIXME" --from-literal="SPOTIFY_CLIENT_SECRET=FIXME"> blabla-secrets.yaml
1. download https://raw.githubusercontent.com/baloise-incubator/okd4-cluster-infra-apps/refs/heads/master/sealed-secrets/kubeseal.crt > incubator-ca.crt
2. kubeseal --cert incubator-ca.crt --namespace=blabla-spotify -oyaml < blabla-secrets.yaml > sealed-secret.yaml

## Frontend
1. npm run build
1. docker build -t blabla-frontend .
1. docker run -e API_URL=https://my.api.server -p 8080:8080 blabla-frontend
3. docker tag blabla-frontend raffaelheinzer248/blabla-spotify-frontend
4. docker push raffaelheinzer248/blabla-spotify-frontend
1. docker rm -f blabla-frontend

# Links
## Source
- https://github.com/baloise-incubator/cc2024_BlaBlaSpotify

## Spotify
- Web API & Player API: https://developer.spotify.com/
- Our App: https://developer.spotify.com/dashboard/d524fc41ac744eb6878f3a1d29c71f70/settings
- Maybe a good openapi.yaml for the Web API: https://github.com/sonallux/spotify-web-api/blob/main/fixed-spotify-open-api.yml

## SRGSSR
- SRF API Program/Lineup: https://developer.srgssr.ch/api-catalog/srgssr-epg-v3
- SRF API Channels, Streams, etc.: https://developer.srgssr.ch/api-catalog/srgssr-audio-0

## Incubator
- Incubator: https://console.baloise.dev/add/all-namespaces
- Incubator Deployments: https://github.com/baloise-incubator/code-camp-apps
- ArgoCD: https://argocd.baloise.dev/
- Sealed Secrets: https://confluence.baloisenet.com/pages/viewpage.action?pageId=1867186764
- Sealed Secrets CA: https://github.com/baloise-incubator/okd4-cluster-infra-apps/blob/master/sealed-secrets/kubeseal.crt

## Misc
- Material UI: https://m3.material.io/
- Material UI for Angular: https://material.angular.io/
- Radio Browser API with thousands of radio streams: https://www.radio-browser.info/
- Inofficial TuneIn API docs: https://tunein-api.corehacked.com/
- Media Elements for various services (not tried): https://github.com/muxinc/media-elements
