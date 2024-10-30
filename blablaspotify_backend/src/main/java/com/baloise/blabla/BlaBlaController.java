package com.baloise.blabla;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


record RadioStation(String name, String epgId, String streamUri) {}

record RadioStationDto(String title, String epgId, String imageUrl, String imageTitle) {}

@RestController
@RequestMapping("/blabla")
@RequiredArgsConstructor
public class BlaBlaController {

    private final SrfBackend srfBackend;

    private final Map<String, String> STATION_TITLE_TO_EPG = Map.of(
        "Radio SRF 1", "srf-1",
        "Radio SRF 2 Kultur", "srf-2-kultur",
        "Radio SRF 3", "srf-3",
        "Radio SRF 4 News", "srf-4",
        "Radio SRF Musikwelle", "srf-musikwelle",
        "Radio SRF Virus", "srf-virus"
    );

    @GetMapping("/stations")
    public List<RadioStationDto> stations() throws Exception {
        return this.srfBackend.getStations().stream()
            .filter(station -> station.transmission().equals("RADIO"))
            .map(station -> new RadioStationDto(
                station.title(),
                this.STATION_TITLE_TO_EPG.getOrDefault(station.title(), ""),
                station.imageUrl(),
                station.imageTitle()))
            .toList();
    }

    @GetMapping("/epg/{stationId}")
    public String getMethodName(@RequestParam String stationId) {
        return new String();
    }
    
}
