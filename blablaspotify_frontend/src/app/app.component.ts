import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatToolbar } from '@angular/material/toolbar';
import { PlayerComponent } from "./player/player.component";
import { MatGridList, MatGridTile } from '@angular/material/grid-list';
import { MatCard, MatCardContent, MatCardHeader, MatCardTitle } from '@angular/material/card';
import { StationSearchComponent } from "./station-search/station-search.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet, MatToolbar, PlayerComponent, MatGridList, MatGridTile, MatCard, MatCardHeader, MatCardTitle,
    MatCardContent,
    StationSearchComponent
],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'blablaspotify_frontend';
}
