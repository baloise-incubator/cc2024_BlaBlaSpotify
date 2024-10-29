import { Injectable } from '@angular/core';
import {SpotifyService} from './music/spotify.service';

@Injectable({
  providedIn: 'root'
})
export class TumblerService {

  isBlabla = false;

  constructor(private spotifyService: SpotifyService) {
  }

  public toggle() {
    this.isBlabla = !this.isBlabla;
    this.activate();
  }

  private activate() {
    if (this.isBlabla) {
      this.activateBlabla();
    } else {
      this.activateMusic();
    }
  }

  public activateBlabla() {
    this.spotifyService.pause();
    console.log('Blabla activated');
  }

  public activateMusic() {
    this.spotifyService.play();
    console.log('Music activated');
  }
}
