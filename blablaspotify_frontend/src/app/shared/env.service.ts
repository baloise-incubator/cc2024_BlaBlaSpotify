import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvService {
  public apiUrl: string = (window as any).__env?.API_URL || 'http://localhost:4200';
}
