package com.baloise.blabla;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

record RadioStation(String name, String epgId) {}

@RestController
@RequestMapping("/blabla")
public class BlaBlaController {

    @Value("${blabla.consumer_key}")
    private String consumerKey;

    @Value("${blabla.consumer_secret}")
    private String consumerSecret;

    // "srf-1", "srf-2", "srf-2-kultur", "srf-3", "srf-4", "srf-musikwelle", "srf-virus"
    private final Map<Integer, RadioStation> STATIONS = Map.of(
        1, new RadioStation("SRF 1", "srf-1"),
        2, new RadioStation("SRF 2", "srf-2"),
        3, new RadioStation("SRF 2 Kultur", "srf-2-kultur"),
        4, new RadioStation("SRF 4", "srf-4"),
        5, new RadioStation("SRF Musikwelle", "srf-musikwelle"),
        6, new RadioStation("SRF Virus", "srf-virus")
    );

    @GetMapping("/stations")
    public Map<Integer, RadioStation> stations() {
        return this.STATIONS;
    }
}
