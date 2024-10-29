import {NgIf} from '@angular/common';
import { Component } from '@angular/core';
import {TumblerService} from '../tumbler.service';

@Component({
  selector: 'app-toggler',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './toggler.component.html',
  styleUrl: './toggler.component.scss'
})
export class TogglerComponent {

  constructor(public tumblerService: TumblerService) {
  }

}
