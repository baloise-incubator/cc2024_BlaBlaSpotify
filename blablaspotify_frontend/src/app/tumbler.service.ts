import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TumblerService {

  isBlabla = false;

  public toggle() {
    this.isBlabla = !this.isBlabla;
    this.activate();
  }

  private activate() {
    if (this.isBlabla) {
      this.activateBlabla();
    } else {
      this.activateMusic();
    }
  }

  public activateBlabla() {
    console.log('Blabla activated');
  }

  public activateMusic() {
    console.log('Music activated');
  }
}
