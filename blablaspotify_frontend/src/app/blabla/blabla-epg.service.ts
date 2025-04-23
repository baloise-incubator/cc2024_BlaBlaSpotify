import {HttpClient} from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Subscription, timer } from 'rxjs';
import { isAfter, subSeconds } from 'date-fns'
import { ControlsService } from '../controls/controls.service';
import {EnvService} from '../shared/env.service';
import {Program, ProgramDateTimes, ProgramList} from '../types';

@Injectable({ providedIn: 'root' })
export class BlaBlaEpgService {
    private _controlsService = inject(ControlsService)
    private _httpClient = inject(HttpClient)
    private _subscriptions: Subscription[] = []

    constructor(private envService: EnvService) {
    }

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

        if (stationId === 'srf-1-demo') {
            console.log('demo station');
            this.scheduleBlaBla(this.createDemoStationProgramList());
        } else {
            this.setProgramGuide(stationId);
        }
    }

    setProgramGuide(stationId: string) {
        this._httpClient.get(this.envService.apiUrl + '/blabla/programGuides/' + stationId).subscribe({
            next: (response: any) => {
                this.scheduleBlaBla(response as ProgramList);
            },
            error: (error: any) => {
                console.error('Failed to get program list: ', error)
            }
        });
    }

    createDemoStationProgramList(): ProgramList {
        let programs = [];
        for (let i = 0; i < 50; i++) {
            programs.push(this.createDemoProgram(i));
        }
        return {
            programs: programs
        } as ProgramList;
    }

    private createDemoProgram(i: number): Program {
        const defaultDurationSeconds = 15;
        const startDate = new Date();
        startDate.setUTCSeconds(startDate.getUTCSeconds() + (defaultDurationSeconds * 2) * i);
        const endDate = new Date(startDate.getTime());
        endDate.setUTCSeconds(startDate.getUTCSeconds() + defaultDurationSeconds);
        return {
            title: 'Demo Episode ' + i,
            shortDescription: 'Demo Episode ' + i,
            dateTimes: {
                startTime: startDate.toISOString(),
                endTime: endDate.toISOString(),
                duration: defaultDurationSeconds.toString(),
                timezone: 'UTC'
            } as ProgramDateTimes
        } as Program;
    }
}
