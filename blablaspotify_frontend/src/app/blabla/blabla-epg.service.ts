import { inject, Injectable } from '@angular/core';
import { Subscription, timer } from 'rxjs';
import { isAfter, subSeconds } from 'date-fns'
import { ControlsService } from '../controls/controls.service';
import { Program } from '../types';

@Injectable({ providedIn: 'root' })
export class BlaBlaEpgService {
    private _controlsService = inject(ControlsService)
    private _subscriptions: Subscription[] = []

    scheduleBlaBla(lineup: Program[]) {
        this.cancelExistingSchedule()

        this._subscriptions = lineup.flatMap(program => this.createScheduleEntry(program))
        .filter(scheduleEntry => isAfter(scheduleEntry.on, new Date()))
        .map(scheduleEntry => timer(scheduleEntry.on)
            .subscribe(() => this._controlsService.setPlayBackSource(scheduleEntry.type === 'start' ? 'blabla': 'music'))
        )
    }

    private createScheduleEntry(porgram: Program) {
        return [
            { type: 'start', on: subSeconds(new Date(porgram.dateTimes.startTime), 1.5) },
            { type: 'end', on: new Date(porgram.dateTimes.endTime) }
        ]
    }

    private cancelExistingSchedule() {
        this._subscriptions.forEach(subscription => subscription.unsubscribe())
        this._subscriptions = []
    }
}