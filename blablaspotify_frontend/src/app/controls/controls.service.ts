import { computed, effect, inject, Injectable, signal } from "@angular/core";
import { SpotifyService } from "../music/spotify.service";

export type SourceType = 'blabla' | 'music'

@Injectable({ providedIn: 'root' })
export class ControlsService {
    private spotifyService = inject(SpotifyService)

    private readonly _playBackSource = signal<SourceType>('music')
    private readonly _playBackStatus = signal<'playing' | 'paused'>('paused')

    readonly playBackState = computed(() =>
        ({ source: this._playBackSource(), status: this._playBackStatus() }))

    togglePlayBackSource() {
        if (this._playBackSource() === 'blabla') {
            this._playBackSource.set('music')
        } else {
            this._playBackSource.set('blabla')
        }
    }

    setPlayBackSource(newSource: SourceType) {
        this._playBackSource.set(newSource)
    }

    togglePlayBack() {
        if (this._playBackStatus() === 'paused') {
            this._playBackStatus.set('playing')
        } else {
            this._playBackStatus.set('paused')
        }
    }

    private _handleSpotify = effect(() => {
        const playBackSource = this._playBackSource()
        const playBackStatus = this._playBackStatus()

        if (playBackSource === 'music' && playBackStatus === 'playing') {
            console.log('Start Spotify')
            this.spotifyService.play()
        } else {
            console.log('Stop Spotify')
            this.spotifyService.pause()
        }
    })
}