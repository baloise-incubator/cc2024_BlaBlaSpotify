import {Component} from '@angular/core';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {MatGridList, MatGridTile} from '@angular/material/grid-list';
import {MatToolbar} from '@angular/material/toolbar';
import {RouterOutlet} from '@angular/router';
import {BlablaComponent} from './blabla/blabla.component';
import {PlayerComponent} from './blabla/player/player.component';
import {StationSearchComponent} from './blabla/station-search/station-search.component';
import {MusicComponent} from './music/music.component';
import {TogglerComponent} from './toggler/toggler.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet, MatToolbar, PlayerComponent, MatGridList, MatGridTile, MatCard, MatCardHeader, MatCardTitle,
    MatCardContent,
    StationSearchComponent, BlablaComponent, MusicComponent, TogglerComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'blablaspotify_frontend';
}
