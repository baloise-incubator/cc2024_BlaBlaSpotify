import {HttpClient} from '@angular/common/http';
import {Injectable, OnInit} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SpotifyService {

  isAuthorized= false;

  accessToken = undefined;

  constructor(private httpClient: HttpClient) {
    this.init()
  }

  private init() {
    this.httpClient.get('/api/auth/token').subscribe({
      next: (data: any) => {
        if (data) {
          this.accessToken = data.access_token;
          this.isAuthorized = true;
        }
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  authorize() {
    window.location.href = '/api/auth/login';
  }

  getAvatar() {
    return "";
  }
}
