import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

declare global {
  interface Window {
    onSpotifyWebPlaybackSDKReady: () => void;
    Spotify: any;
  }
}

@Injectable({
  providedIn: 'root'
})
export class SpotifyService {

  isAuthorized = false;

  accessToken: string = '';
  avatarUrl: string = '';
  player: any;

  constructor(private httpClient: HttpClient) {
    this.init()
  }

  init() {
    this.httpClient.get('/api/spotify/auth/token').subscribe({
      next: (data: any) => {
        if (data) {
          this.accessToken = data.access_token;
          this.isAuthorized = true;
          this.initAvatarUrl();
        }
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  authorize() {
    window.location.href = '/api/spotify/auth/login';
  }

  private initAvatarUrl() {
    this.httpClient.get('/api/spotify/avatar-url').subscribe({
      next: (data: any) => {
        this.avatarUrl = data.url;
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  play() {
    this.player.resume()
  }

  pause() {
    this.player.pause()
  }

  initPlayer() {
    this.player = new window.Spotify.Player({
      name: 'CodeCamp Player',
      getOAuthToken: (cb: (token: string) => void) => {
        cb(this.accessToken);
      },
      volume: 0.5
    });
    this.player.connect();
    this.play();
  }
}
