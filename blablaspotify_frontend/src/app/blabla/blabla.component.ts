import { Component, inject } from '@angular/core';
import {MatGridList, MatGridTile} from '@angular/material/grid-list';
import {PlayerComponent} from './player/player.component';
import {StationSearchComponent} from './station-search/station-search.component';
import { BlaBlaService } from './blabla-service';

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
}
