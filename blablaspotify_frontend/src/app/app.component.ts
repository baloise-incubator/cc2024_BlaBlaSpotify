import {Component, computed, inject} from '@angular/core';
import {MatToolbar} from '@angular/material/toolbar';
import {PlayerComponent} from './blabla/player/player.component';
import {MusicComponent} from './music/music.component';
import { ControlsComponent } from "./controls/controls.component";
import { ControlsService } from './controls/controls.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    MatToolbar,
    PlayerComponent,
    MusicComponent,
    ControlsComponent,
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
