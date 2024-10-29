import { Component } from '@angular/core';
import {MatGridList, MatGridTile} from '@angular/material/grid-list';
import {PlayerComponent} from './player/player.component';
import {StationSearchComponent} from './station-search/station-search.component';

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

}
