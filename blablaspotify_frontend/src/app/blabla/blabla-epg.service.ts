import {HttpClient} from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Subscription, timer } from 'rxjs';
import { isAfter, subSeconds } from 'date-fns'
import { ControlsService } from '../controls/controls.service';
import {Program, ProgramList} from '../types';

@Injectable({ providedIn: 'root' })
export class BlaBlaEpgService {
    private _controlsService = inject(ControlsService)
    private _httpClient = inject(HttpClient)
    private _subscriptions: Subscription[] = []

    scheduleBlaBla(programList: ProgramList) {
        console.log('scheduleBlaBla: ', programList);
        this.cancelExistingSchedule()

        this._subscriptions = programList.programs.flatMap(program => this.createScheduleEntry(program))
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

    stationChanged(stationId: string) {
        console.log('Station changed to: ', stationId);
        this.getProgramList(stationId);
    }

    getProgramList(stationId: string) {
        this._httpClient.get('/api/blabla/programGuides/' + stationId).subscribe({
            next: (response: any) => {
                this.scheduleBlaBla(response as ProgramList);
            },
            error: (error: any) => {
                console.error('Failed to get program list: ', error)
            }
        });
    }
}
