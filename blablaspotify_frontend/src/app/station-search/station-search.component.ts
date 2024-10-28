import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCard, MatCardContent, MatCardHeader, MatCardTitle } from '@angular/material/card';
import { MatFormField } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-station-search',
  standalone: true,
  imports: [
    MatFormField,
    MatInputModule,
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatCardContent,
    MatButtonModule
  ],
  templateUrl: './station-search.component.html',
  styleUrl: './station-search.component.scss'
})
export class StationSearchComponent {

}
