import { Component, computed, signal, viewChild, ElementRef, inject, model, effect, untracked, input } from '@angular/core';
import { MatIconButton } from '@angular/material/button';
import { MatCard, MatCardContent, MatCardHeader, MatCardTitle } from '@angular/material/card';
import { RadioStationFacade } from '../data-access/radio-station.facade';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatOption, MatSelect } from '@angular/material/select';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'blabla-player',
  templateUrl: './player.component.html',
  styleUrl: './player.component.scss',
  standalone: true,
  imports: [
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatFormField,
    MatLabel,
    MatSelect,
    MatOption,
    MatIcon,
    MatIconButton,
  ]
})
export class PlayerComponent {
  readonly isActive = input.required<boolean>()

  private _radioStationFacade = inject(RadioStationFacade)

  protected readonly playerElement = viewChild<ElementRef>('player')

  protected readonly selectedStation = model<string>()
  protected readonly playButton = signal(false)

  protected readonly stationList = this._radioStationFacade.stations

  protected readonly stationUrl = computed(() => this.getStreamUrl(this.selectedStation()))
  protected readonly playButtonDisabled = computed(() => this.stationUrl().length == 0)
  protected readonly isPlaying = computed(() => this.isActive() && this.playButton() && !this.playButtonDisabled())
  protected readonly playButtonText = computed(() => this.isPlaying() ? "Pause" : "Play")

  private _handlePlayerStatus = effect(() => {
    const isPlaying = this.isPlaying()

    untracked(() => {
      const player = this.playerElement()?.nativeElement as HTMLAudioElement

      if (isPlaying) {
        player.load()
        player.play()
      } else {
        player.pause()
      }
    })
  })

  protected pausePlayBack() {
    this.playButton.set(false)
  }

  protected togglePlayback() {
    const current = this.playButton()
    this.playButton.set(!current)
  }

  private getStreamUrl(epgId?: string) {
    const station = this._radioStationFacade.stations().find(station => station.epgId === epgId)
    return station === undefined ? '' : station.streamUrl
  }
}
