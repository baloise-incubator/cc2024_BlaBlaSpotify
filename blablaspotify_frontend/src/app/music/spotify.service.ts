import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable, OnInit} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SpotifyService {

  isAuthorized= false;

  accessToken = undefined;
  avatarUrl = undefined;

  constructor(private httpClient: HttpClient) {
    this.init()
  }

  private init() {
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
}
