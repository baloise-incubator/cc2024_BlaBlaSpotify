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

  protected readonly playerElement = viewChild<ElementRef<HTMLAudioElement>>('player')

  protected readonly selectedStation = model<string>(this._radioStationFacade.stations()[0].epgId)

  protected readonly stationList = this._radioStationFacade.stations

  protected readonly stationUrl = computed(() => this.getStreamUrl(this.selectedStation()))

  private _handlePlayerStatus = effect(() => {
    const isPlaying = this.isActive()
    const stationUrl = this.stationUrl()

    untracked(() => {
      const player = this.playerElement()?.nativeElement
      player!.src = stationUrl

      if (isPlaying) {
        player!.load()
        //player.play()
      } else {
        player!.pause()
      }
    })
  })

  protected startPlayBack() {
    if (this.isActive()) {
      this.playerElement()!.nativeElement.play()
    }
  }

  private getStreamUrl(epgId?: string) {
    const station = this._radioStationFacade.stations().find(station => station.epgId === epgId)
    return station === undefined ? '' : station.streamUrl
  }
}
