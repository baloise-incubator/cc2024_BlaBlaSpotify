package com.baloise.spotify;

import com.baloise.springtutorialbackend.SslUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/spotify/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private static final String ENCODING = StandardCharsets.UTF_8.toString();
    private static final String CLIENT_ID = "d524fc41ac744eb6878f3a1d29c71f70";
    private static final String CLIENT_SECRET = "4c47e069aaed46cdb10f875d2e3dabc5";
    private static final String REDIRECT_URI = "http://localhost:8080/spotify/auth/callback";

    private final AuthenticationStore authenticationStore;
    private final RestTemplate restTemplate;

    @GetMapping("/login")
    public RedirectView login() throws Exception {
        String targetUrl = "https://accounts.spotify.com/authorize"
                + "?client_id=" + URLEncoder.encode(CLIENT_ID, ENCODING)
                + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, ENCODING)
                + "&response_type=" + URLEncoder.encode("code", ENCODING)
                + "&scope=" + URLEncoder.encode("user-modify-playback-state user-read-playback-state streaming user-read-email user-read-private playlist-read-private", ENCODING)
                + "&state=" + URLEncoder.encode(generateState(), ENCODING);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(targetUrl);
        return redirectView;
    }

    @GetMapping("/callback")
    public RedirectView callback(@RequestParam("code") String code) {
        SslUtils.disableSSLCertificateChecking();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("code", code);
        requestBody.add("redirect_uri", REDIRECT_URI);
        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("client_secret", CLIENT_SECRET);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://accounts.spotify.com/api/token",
                HttpMethod.POST,
                new HttpEntity<>(requestBody, headers),
                String.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            authenticationStore.setJwtToken(response.getBody());

            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("http://localhost:4200");
            return redirectView;
        } else {
            throw new RuntimeException();
        }
    }

    @GetMapping("/token")
    public String token() {
        return authenticationStore.getJwtToken();
    }

    private String generateState() {
        return "could-be-more-random-than-this";
    }


}