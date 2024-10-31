import { Component, computed, signal, viewChild, ElementRef, inject, model, effect, untracked, input } from '@angular/core';
import { MatIconButton } from '@angular/material/button';
import { MatCard, MatCardContent, MatCardHeader, MatCardTitle } from '@angular/material/card';
import {BlaBlaEpgService} from '../blabla-epg.service';
import { RadioStationFacade } from '../data-access/radio-station.facade';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatOption, MatSelect } from '@angular/material/select';
import { MatIcon } from '@angular/material/icon';
import { interval, Subscription, take } from 'rxjs';

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
  private _epgService = inject(BlaBlaEpgService)

  protected readonly playerElement = viewChild<ElementRef<HTMLAudioElement>>('player')

  protected readonly selectedStation = model<string>(this._radioStationFacade.stations()[0].epgId)

  protected readonly stationList = this._radioStationFacade.stations

  protected readonly stationUrl = computed(() => this.getStreamUrl(this.selectedStation()))

  private _fadeSubscription?: Subscription

  private _handlePlayerStatus = effect(() => {
    const isPlaying = this.isActive()
    const stationUrl = this.stationUrl()

    untracked(() => {
      const player = this.playerElement()?.nativeElement

      console.log(stationUrl)

      if (isPlaying) {
        player!.src = stationUrl
        player!.load()
      } else {
        this.stopPlayBack()
      }
    })
  })

  protected startPlayBack() {
    if (this.isActive()) {
      const player = this.playerElement()!.nativeElement
      player.volume = 0
      player.play()
      this.cleanupFadeSubscription()
      this._fadeSubscription = interval(300).pipe(take(5)).subscribe((i) => player.volume = (i + 1) * 0.2)
    }
  }

  protected stopPlayBack() {
    const player = this.playerElement()!.nativeElement
    if (!player.paused) {
      this.cleanupFadeSubscription()
      interval(300).pipe(take(5)).subscribe({
        next: (i) => player.volume = 1 - ((i + 1) * 0.2),
        complete: () => player.pause()
      })
    }
  }

  private cleanupFadeSubscription() {
    if (this._fadeSubscription !== undefined) {
      this._fadeSubscription.unsubscribe()
      this._fadeSubscription = undefined
    }
  }

  private getStreamUrl(epgId?: string) {
    const station = this._radioStationFacade.stations().find(station => station.epgId === epgId)
    return station === undefined ? '' : station.streamUrl
  }

  onStationValueChange() {
    this._epgService.stationChanged(this.selectedStation());
  }
}
