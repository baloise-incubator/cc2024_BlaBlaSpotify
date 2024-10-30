import {JsonPipe, NgIf} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {MusicPlayerComponent} from './player/music-player.component';
import {SpotifyService} from './spotify.service';

@Component({
  selector: 'app-music',
  standalone: true,
  imports: [
    MusicPlayerComponent
  ],
  templateUrl: './music.component.html',
  styleUrl: './music.component.scss'
})
export class MusicComponent {
}
