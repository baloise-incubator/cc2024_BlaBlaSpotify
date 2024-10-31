package com.baloise.blabla;

import com.baloise.blabla.api.ProgramList;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

@RestController
@RequestMapping("/blabla")
@RequiredArgsConstructor
public class BlaBlaRestController {

    private final SrfAuthenticationStore srfAuthenticationStore;

    private final RestTemplate restTemplate;

    @GetMapping("/programGuides/{program}")
    public ProgramList getProgramGuide(@PathVariable("program") String program) throws Exception {
        HttpHeaders headers = srfAuthenticationStore.createHeaders();
        headers.set("Accept", "application/json");
        headers.set("Accept-Encoding", "gzip");

        ResponseEntity<byte[]> response = restTemplate.exchange(
                "https://api.srgssr.ch/epg/v3/srf/radio/stations/" + program,
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                byte[].class
        );

        try (GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(response.getBody()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(gis))) {
            return new ObjectMapper().readValue(reader, ProgramList.class);
        }
    }
}
