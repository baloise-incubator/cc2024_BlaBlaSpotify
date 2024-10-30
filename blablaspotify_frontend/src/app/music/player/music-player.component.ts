import {NgIf} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {MatButton} from '@angular/material/button';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {MatOption} from '@angular/material/core';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatSelect} from '@angular/material/select';
import {Playlist} from '../model';
import {SpotifyService} from '../spotify.service';

@Component({
  selector: 'music-player',
  standalone: true,
  imports: [
    MatButton,
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatFormField,
    MatLabel,
    MatOption,
    MatSelect,
    NgIf
  ],
  templateUrl: './music-player.component.html',
  styleUrl: './music-player.component.scss'
})
export class MusicPlayerComponent implements OnInit {

  selectedPlaylist: Playlist | undefined;
  isPlaying = false;

  constructor(public spotifyService: SpotifyService) {
  }

  ngOnInit() {
    let windowRef = window as any;
    windowRef.onSpotifyWebPlaybackSDKReady = () => {
      this.spotifyService.initPlayer();
      console.log("inited")
    };
  }

  playlists(): Playlist[] {
    return this.spotifyService.playlists;
  }

  pausePlayBack() {
    this.isPlaying = false;

  }

  togglePlayback() {
    this.isPlaying = !this.isPlaying;
  }

  playButtonDisabled() {
    return false;
  }

  playButtonText() {
    return this.isPlaying ? "Pause" : "Play";
  }
}
