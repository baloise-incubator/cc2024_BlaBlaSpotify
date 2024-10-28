import { Component, computed, signal, viewChild, ElementRef } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCard, MatCardContent, MatCardHeader, MatCardTitle } from '@angular/material/card';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrl: './player.component.scss',
  standalone: true,
  imports: [
    MatButtonModule,
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle
  ]
})
export class PlayerComponent {
  protected readonly playerElement = viewChild<ElementRef>('player')
  protected readonly stationUrl = signal('http://stream.srg-ssr.ch/m/drs3/mp3_128')
  protected readonly isPlaying = signal(false)
  protected readonly playButtonText = computed(() => this.isPlaying() ? "Pause" : "Wiedergabe" )

  protected togglePlayback() {
    const player = this.playerElement()?.nativeElement as HTMLAudioElement
    if (this.isPlaying()) {
      player.pause()
    } else {
      player.load()
      player.play()
    }
  }
}
