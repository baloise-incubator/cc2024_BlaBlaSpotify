import { Injectable } from '@angular/core';
import {SpotifyService} from './music/spotify.service';
import { BlaBlaService } from './blabla/blabla-service';

@Injectable({
  providedIn: 'root'
})
export class TumblerService {

  isBlabla = false;

  constructor(private spotifyService: SpotifyService, private blablaService: BlaBlaService) {
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
    this.blablaService.play();
  }

  public activateMusic() {
    this.spotifyService.play();
    this.blablaService.pause();
  }
}
