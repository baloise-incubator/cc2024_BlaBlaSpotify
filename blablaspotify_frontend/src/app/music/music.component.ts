import {NgIf} from '@angular/common';
import {HttpClient} from '@angular/common/http';
import { Component } from '@angular/core';
import {SpotifyService} from './spotify.service';

@Component({
  selector: 'app-music',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './music.component.html',
  styleUrl: './music.component.scss'
})
export class MusicComponent {

  constructor(public spotifyService: SpotifyService) {
  }


}
