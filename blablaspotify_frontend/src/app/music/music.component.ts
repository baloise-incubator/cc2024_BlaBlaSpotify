import {NgIf} from '@angular/common';
import {Component, OnInit} from '@angular/core';
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
export class MusicComponent implements OnInit {

  constructor(public spotifyService: SpotifyService) {
  }

  ngOnInit() {
    let windowRef = window as any;
    windowRef.onSpotifyWebPlaybackSDKReady = () => {
      this.spotifyService.initPlayer();
      console.log("inited")
    };
  }

}
