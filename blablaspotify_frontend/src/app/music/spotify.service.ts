import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PlaylistList, User } from '../types';
import { Subscription } from 'rxjs';
import { createFader } from '../shared/fade.util';

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
  playlistList?: PlaylistList;
  currentPlayListUrn?: string;
  nextPlayListUrn?: string;
  isActive = false;

  fadeSubscription?: Subscription

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

    this.player.setVolume(0)
    this.fadeSubscription = createFader(0.1).subscribe(v => this.player.setVolume(v))
  }

  pause() {
    this.isActive = false;

    if (this.player) {
      this.player.getCurrentState().then((state: { paused: any; }) => {
        if (state && !state.paused) {
          this.fadeSubscription = createFader(-0.1, 0.5).subscribe({
            next: (v) => this.player?.setVolume(v),
            complete: () => this.player?.pause()
          })
        }
      })
    }
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
        this.playlistList = data as PlaylistList;
        this.nextPlayListUrn = this.playlistList.items[0].uri;
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
}
