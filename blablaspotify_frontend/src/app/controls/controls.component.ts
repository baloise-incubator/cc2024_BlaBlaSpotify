import { Component, computed, effect, inject, model } from '@angular/core';
import { ControlsService, SourceType } from './controls.service';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule, MatFabButton } from '@angular/material/button';
import { MatButtonToggle, MatButtonToggleGroup } from '@angular/material/button-toggle';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-controls',
  standalone: true,
  imports: [
    MatCardModule,
    MatButtonToggle,
    MatButtonToggleGroup,
    MatIcon,
    MatButtonModule,
  ],
  templateUrl: './controls.component.html',
  styleUrl: './controls.component.scss'
})
export class ControlsComponent {
  private readonly _controlsService = inject(ControlsService)

  protected readonly selectedSource = computed(() => this._controlsService.playBackState().source)
  protected readonly isPlaying = computed(() => this._controlsService.playBackState().status === 'playing')

  protected onClickPlayButton() {
    this._controlsService.togglePlayBack()
  }

  protected onSourceValueChange(newSource: SourceType) {
    this._controlsService.setPlayBackSource(newSource)
  }
}
