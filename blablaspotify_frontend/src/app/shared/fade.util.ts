import { interval, map, take } from "rxjs";

export function createFader(increment: number, start = 1, msPerStep = 300, steps = 5) {
    return interval(msPerStep).pipe(
        take(steps),
        map(step => {
            const factor = step + 1
            return increment < 0
                ? start - (factor * Math.abs(increment))
                : factor * increment
        }),
    )
}