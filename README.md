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
1. Nice UI for Spotify (Playlists, Artists, Skip Button, Login etc.)
1. Radio station selectable from 50'000 stations
1. Go-Mobile: PWA
1. Android/iOS app

# Try it out
<link here>

# Deploy it on incubator
## Backend
1. mvn clean install
1. docker build -t blabla-backend .
1. docker run -p 8080:8080 blabla-backend
1. docker logs -f blabla-backend
2. docker rm -f blabla-backend
3. oc create secret generic my-db-secret --dry-run=client -o yaml --from-literal="SRF_CONSUMER_KEY=FIXME" --from-literal="SRF_CONSUMER_SECRET=FIXME" --from-literal="SPOTIFY_CLIENT_ID=FIXME" --from-literal="SPOTIFY_CLIENT_SECRET=FIXME"> blabla-secrets.yaml
1. kubeseal --cert https://raw.githubusercontent.com/baloise-incubator/okd4-cluster-infra-apps/refs/heads/master/sealed-secrets/kubeseal.crt --namespace=blabla-spotify -oyaml < blabla-secrets.yaml > namespaced-sealed-secret.yaml

## Frontend
1. npm run build
1. docker build -t blabla-frontend .
1. docker run -p 4200:8080 blabla-frontend
1. docker rm -f blabla-frontend
