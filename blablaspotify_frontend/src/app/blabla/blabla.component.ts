import { Component, computed, inject } from '@angular/core';
import {MatGridList, MatGridTile} from '@angular/material/grid-list';
import {PlayerComponent} from './player/player.component';
import {StationSearchComponent} from './station-search/station-search.component';
import { BlaBlaService } from './blabla-service';
import { ControlsService } from '../controls/controls.service';

@Component({
  selector: 'app-blabla',
  standalone: true,
  imports: [
    MatGridTile,
    PlayerComponent,
    StationSearchComponent,
    MatGridList
  ],
  templateUrl: './blabla.component.html',
  styleUrl: './blabla.component.scss'
})
export class BlablaComponent {
  protected readonly blablaService = inject(BlaBlaService)
  private readonly _controlsService = inject(ControlsService)

  protected isPlaying = computed(() => {
    const controlsState = this._controlsService.playBackState()
    return controlsState.source === 'blabla' && controlsState.status === 'playing'
  })
}
