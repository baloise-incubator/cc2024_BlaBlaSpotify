import {JsonPipe, NgIf} from '@angular/common';
import {Component, effect, input, OnInit} from '@angular/core';
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
  readonly isActive = input.required<boolean>()

  constructor(private spotifyService: SpotifyService) {}

  private handleIsActiveChange = effect(() => {
    if (this.isActive()) {
      this.spotifyService.play()
    } else {
      this.spotifyService.pause()
    }
  })
}
