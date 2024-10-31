package com.baloise.blabla;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class SrfAuthenticationStore {

    @Value("${blabla.consumer_key}")
    private String consumerKey;

    @Value("${blabla.consumer_secret}")
    private String consumerSecret;

    private final RestTemplate restTemplate;

    private String jwtToken = null;

    public HttpHeaders createHeaders() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + getAccessToken());
        return headers;
    }

    public String getAccessToken() throws JsonProcessingException {
        if (jwtToken == null) {
            jwtToken = this.generateApiToken();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jwtToken);
        return jsonNode.get("access_token").asText();
    }

    private String generateApiToken() {
        String authorization = this.consumerKey + ":" + this.consumerSecret;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", Base64.getEncoder().encodeToString(authorization.getBytes()));
        headers.setCacheControl(CacheControl.noCache());
        headers.setContentLength(0);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.srgssr.ch/oauth/v1/accesstoken?grant_type=client_credentials",
                HttpMethod.POST,
                new HttpEntity<>(null, headers),
                String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException();
        }
    }
}
