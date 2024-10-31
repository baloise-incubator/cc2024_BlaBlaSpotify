import { Injectable, Signal, signal } from "@angular/core"

type RadionStation = {
    epgId: 'srf-1-demo' | 'srf-1' | 'srf-2' | 'srf-2-kultur' | 'srf-3' | 'srf-4' | 'srf-musikwelle' | 'srf-virus'
    name: string
    streamUrl: string
}

@Injectable({ providedIn: 'root' })
export class RadioStationFacade {

    private readonly _stations = signal<RadionStation[]>([
        { epgId: 'srf-1-demo', name: 'Radio SRF 1 DEMO', streamUrl: 'http://stream.srg-ssr.ch/m/drs4news/mp3_128' },
        { epgId: 'srf-1', name: 'Radio SRF 1', streamUrl: 'http://stream.srg-ssr.ch/m/drs1/mp3_128' },
        { epgId: 'srf-2-kultur', name: 'Radio SRF 2 Kultur', streamUrl: 'http://stream.srg-ssr.ch/drs2/mp3_128' },
        { epgId: 'srf-3', name: 'Radio SRF 3', streamUrl: 'http://stream.srg-ssr.ch/m/drs3/mp3_128' },
        { epgId: 'srf-4', name: 'Radio SRF 4 News', streamUrl: 'http://stream.srg-ssr.ch/m/drs4news/mp3_128' },
        { epgId: 'srf-musikwelle', name: 'Radio SRF Musikwelle', streamUrl: 'http://stream.srg-ssr.ch/m/drsmw/mp3_128' },
        { epgId: 'srf-virus', name: 'Radio SRF Virus', streamUrl: 'http://stream.srg-ssr.ch/drsvirus/mp3_128' },
    ])

    readonly stations: Signal<RadionStation[]> = this._stations
}
