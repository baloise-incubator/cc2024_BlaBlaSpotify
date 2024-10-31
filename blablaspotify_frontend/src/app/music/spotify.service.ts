import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {User} from '../types';
import {Playlist} from './model';

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
  user?: User;
  player: any;
  playlists: Playlist[] = [];
  currentPlayListUrn?: string;
  nextPlayListUrn?: string;
  isActive = false;

  constructor(private httpClient: HttpClient) {
    this.init()
  }

  init() {
    this.httpClient.get('/api/spotify/auth/token').subscribe({
      next: (data: any) => {
        if (data) {
          this.accessToken = data.access_token;
          this.isAuthorized = true;
          this.initUser();
          this.initPlaylists();
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

  private initUser() {
    this.httpClient.get('/api/spotify/user').subscribe({
      next: (data: any) => {
        this.user = data;
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  changePlayListUrn(playListUrn: string) {
    this.nextPlayListUrn = playListUrn

    if (this.isActive && this.nextPlayListUrn !== this.currentPlayListUrn) {
      this.play(this.nextPlayListUrn)
    }
  }

  play(urn?: string | undefined) {
    this.isActive = true;

    if (!urn && this.player && this.currentPlayListUrn !== undefined) {
      this.player.resume()
    } else {
      this.currentPlayListUrn = urn ? urn : this.nextPlayListUrn
      this.httpClient.get('/api/spotify/play?uri=' + this.currentPlayListUrn).subscribe({
        next: (data: any) => console.log('playlist started'),
        error: (error) => console.error(error)
      });
    }
  }

  pause() {
    this.isActive = false;
    this.player?.pause()
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
  }

  private initPlaylists() {
    this.httpClient.get('/api/spotify/playlists').subscribe({
      next: (data: any) => {
        this.playlists = (data.items as []).map((playlist: any) => {
          let p = new Playlist();
          p.title = playlist.name;
          p.urn = playlist.uri;
          p.imageUrl = playlist.images?.[0].url ?? '';
          return p;
        });
        this.nextPlayListUrn = this.playlists[0].urn
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
}
