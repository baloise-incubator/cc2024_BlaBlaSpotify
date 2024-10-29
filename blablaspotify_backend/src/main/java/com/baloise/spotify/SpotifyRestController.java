package com.baloise.spotify;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/spotify")
@RequiredArgsConstructor
public class SpotifyRestController {

    private final AuthenticationStore authenticationStore;
    private final RestTemplate restTemplate;

    @GetMapping("/me")
    public String me() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.spotify.com/v1/me",
                HttpMethod.GET,
                new HttpEntity<>(null, createHeaders()),
                String.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return jsonNode.get("id").asText();
        } else {
            throw new RuntimeException();
        }
    }

    @GetMapping("/avatar-url")
    public String avatarUrl() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.spotify.com/v1/users/" + me(),
                HttpMethod.GET,
                new HttpEntity<>(null, createHeaders()),
                String.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return jsonNode.get("images").get(0).toString();
        } else {
            throw new RuntimeException();
        }
    }


    @GetMapping("/play")
    public void play(@RequestParam("uri") String uri) throws Exception {
        MultiValueMap<String, String> headers = createHeaders();
        headers.add("Content-Type", "application/json");

        uri = "spotify:playlist:37i9dQZF1DX05xCBTd43pw";
        String deviceId = deviceId();
        System.out.println("Device ID: " + deviceId);

        HttpEntity<String> entity = new HttpEntity<>("{\"context_uri\": [\"" + uri + "\"], \"play\": true}", headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.spotify.com/v1/me/player/play?device_id=" + deviceId,
                HttpMethod.PUT,
                new HttpEntity<>(new HttpEntity<>(entity), headers),
                String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException();
        }
    }

    private String deviceId() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.spotify.com/v1/me/player/devices",
                HttpMethod.GET,
                new HttpEntity<>(null, createHeaders()),
                String.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return jsonNode.get("devices").get(1).get("id").toString();
        } else {
            throw new RuntimeException();
        }
    }

    private MultiValueMap<String, String> createHeaders() throws Exception {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authenticationStore.getAccessToken());
        return headers;
    }

}
