import { Component, computed, signal, viewChild, ElementRef, inject, model, effect, untracked } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCard, MatCardContent, MatCardHeader, MatCardTitle } from '@angular/material/card';
import { RadioStationFacade } from '../data-access/radio-station.facade';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatOption, MatSelect } from '@angular/material/select';

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
    MatCardTitle,
    MatFormField,
    MatLabel,
    MatSelect,
    MatOption
  ]
})
export class PlayerComponent {
  private _radioStationFacade = inject(RadioStationFacade)

  protected readonly playerElement = viewChild<ElementRef>('player')
  protected readonly stationUrl = computed(() => this.getStreamUrl(this.selectedStation()))
  protected readonly playButtonDisabled = computed(() => this.stationUrl().length == 0)
  protected readonly isPlaying = signal(false)
  protected readonly playButtonText = computed(() => this.isPlaying() ? "Pause" : "Wiedergabe")
  protected readonly stationList = computed(() => 
    this._radioStationFacade.stations().map(station => ({ epgId: station.epgId, name: station.name })))
  protected readonly selectedStation = model<string>()

  protected pausePlayBack() {
    (this.playerElement()?.nativeElement as HTMLAudioElement).pause()
    this.isPlaying.set(false)
  }

  protected togglePlayback() {
    const player = this.playerElement()?.nativeElement as HTMLAudioElement
    if (this.isPlaying()) {
      console.log('Pause')
      player.pause()
    } else {
      console.log('Play')
      player.load()
      player.play()
    }
  }

  protected onPause() {
    this.isPlaying.set(false)
  }

  private getStreamUrl(epgId?: string) {
    const station = this._radioStationFacade.stations().find(station => station.epgId === epgId)
    return station === undefined ? '' : station.streamUrl
  }
}
