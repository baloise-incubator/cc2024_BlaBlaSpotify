import { Component } from '@angular/core';
import {MatButton} from '@angular/material/button';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {MatOption} from '@angular/material/core';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatSelect} from '@angular/material/select';

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
    MatSelect
  ],
  templateUrl: './music-player.component.html',
  styleUrl: './music-player.component.scss'
})
export class MusicPlayerComponent {
  selectedStation: any;

  stationList() {
    return [];
  }

  pausePlayBack() {

  }

  togglePlayback() {

  }

  playButtonDisabled() {
    return undefined;
  }

  playButtonText() {
    return "";
  }
}
