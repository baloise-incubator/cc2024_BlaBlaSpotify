package com.baloise.spotify;

import com.baloise.spotify.api.DeviceList;
import com.baloise.spotify.api.Play;
import com.baloise.spotify.api.PlaylistList;
import com.baloise.spotify.api.User;
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
        ResponseEntity<User> response = restTemplate.exchange(
                "https://api.spotify.com/v1/me",
                HttpMethod.GET,
                new HttpEntity<>(null, createHeaders()),
                User.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().getId();
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
        System.out.println("URI: " + uri);

        MultiValueMap<String, String> headers = createHeaders();
        headers.add("Content-Type", "application/json");

        String deviceId = deviceId().replaceAll("\"", "");
        System.out.println("Device ID: " + deviceId);

        Play play = Play.builder().context_uri(uri).play(true).build();
        String url = "https://api.spotify.com/v1/me/player/play?device_id=" + deviceId;
        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(play, headers),
                Void.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException();
        }
    }

    private String deviceId() throws Exception {
        ResponseEntity<DeviceList> response = restTemplate.exchange(
                "https://api.spotify.com/v1/me/player/devices",
                HttpMethod.GET,
                new HttpEntity<>(null, createHeaders()),
                DeviceList.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            for (DeviceList.Device device : response.getBody().getDevices()) {
                if (device.getName().contains("CodeCamp")) {
                    return device.getId();
                }
            }
            throw new IllegalArgumentException("No device found");
        } else {
            throw new RuntimeException();
        }
    }

    @GetMapping("/playlists")
    public PlaylistList playlists() throws Exception {
        ResponseEntity<PlaylistList> response = restTemplate.exchange(
                "https://api.spotify.com/v1/me/playlists",
                HttpMethod.GET,
                new HttpEntity<>(null, createHeaders()),
                PlaylistList.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
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
