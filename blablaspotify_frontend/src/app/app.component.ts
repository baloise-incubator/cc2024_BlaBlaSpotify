import {Component, computed, inject} from '@angular/core';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {MatGridList, MatGridTile} from '@angular/material/grid-list';
import {MatToolbar} from '@angular/material/toolbar';
import {RouterOutlet} from '@angular/router';
import {BlablaComponent} from './blabla/blabla.component';
import {PlayerComponent} from './blabla/player/player.component';
import {StationSearchComponent} from './blabla/station-search/station-search.component';
import {MusicComponent} from './music/music.component';
import {TogglerComponent} from './toggler/toggler.component';
import { ControlsComponent } from "./controls/controls.component";
import { ControlsService } from './controls/controls.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet, MatToolbar, PlayerComponent, MatGridList, MatGridTile, MatCard, MatCardHeader, MatCardTitle,
    MatCardContent,
    StationSearchComponent, BlablaComponent, MusicComponent, TogglerComponent,
    ControlsComponent
],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  private _controlsService = inject(ControlsService)

  protected isBlaBlaActive = computed(() => {
    const controlsState = this._controlsService.playBackState()
    return controlsState.source === 'blabla' && controlsState.status === 'playing'
  })
}
