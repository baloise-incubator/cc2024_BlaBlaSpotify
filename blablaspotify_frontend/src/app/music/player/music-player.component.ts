import {NgIf} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {
  MatCard,
  MatCardAvatar,
  MatCardContent,
  MatCardHeader,
  MatCardSubtitle,
  MatCardTitle
} from '@angular/material/card';
import {MatOption} from '@angular/material/core';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatSelect} from '@angular/material/select';
import {Item} from '../../types';
import {SpotifyService} from '../spotify.service';

@Component({
  selector: 'music-player',
  standalone: true,
  imports: [
    MatButtonModule,
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatCardSubtitle,
    MatCardAvatar,
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

  constructor(public spotifyService: SpotifyService) {
  }

  ngOnInit() {
    let windowRef = window as any;
    windowRef.onSpotifyWebPlaybackSDKReady = () => {
      this.spotifyService.initPlayer();
    };
  }

  playlists(): Item[] {
    return this.spotifyService.playlistList?.items || [];
  }

  selectedPlaylist(): string | undefined {
    return this.spotifyService.nextPlayListUrn
  }

  onChangePlaylist(newPlaylist: string) {
    this.spotifyService.changePlayListUrn(newPlaylist)
  }

  createAvatarUrl() {
    if (this.spotifyService.user) {
      return `url(${this.spotifyService.user.images[0].url})`
    }
    return '';
  }
}
