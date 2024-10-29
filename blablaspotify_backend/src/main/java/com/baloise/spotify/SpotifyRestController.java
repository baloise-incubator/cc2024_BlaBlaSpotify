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
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authenticationStore.getAccessToken());

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.spotify.com/v1/me",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
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
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authenticationStore.getAccessToken());

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.spotify.com/v1/users/" + me(),
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
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
}
