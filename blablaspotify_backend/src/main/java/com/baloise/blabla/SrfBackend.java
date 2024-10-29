package com.baloise.blabla;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import java.util.Base64;
import java.util.List;

record ChannelList(@JsonProperty("channelList") List<StationMetaData> channelList) {
};

@Service
@RequiredArgsConstructor
public class SrfBackend {
    @Value("${blabla.consumer_key}")
    private String consumerKey;

    @Value("${blabla.consumer_secret}")
    private String consumerSecret;

    private final RestTemplate restTemplate;

    public List<StationMetaData> getStations() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        this.addTokenToHeaders(headers);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("accept-encoding", "identity");

        ResponseEntity<ChannelList> response = restTemplate.exchange(
                "https://api.srgssr.ch/audiometadata/v2/radio/channels?bu=srf",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                ChannelList.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().channelList();
        } else {
            throw new RuntimeException();
        }
    }

    public String generateApiToken() {
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

    private void addTokenToHeaders(HttpHeaders headers) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(this.generateApiToken());
        String token = jsonNode.get("access_token").asText();

        headers.add("authorization", "Bearer " + token);
    }
}
