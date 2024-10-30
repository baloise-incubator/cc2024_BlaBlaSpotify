import { Injectable, Signal, signal } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class BlaBlaService {
    private readonly _isActive = signal(false)
    readonly isActive: Signal<boolean> = this._isActive

    play() {
        this._isActive.set(true)
    }

    pause() {
        this._isActive.set(false)
    }
}