package com.baloise.springtutorialbackend;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {

    private static final String CLIENT_ID = "d524fc41ac744eb6878f3a1d29c71f70";

    private static final String CLIENT_SECRET = "4c47e069aaed46cdb10f875d2e3dabc5";

    private static final String REDIRECT_URI = "http://127.0.0.1:8080/auth/callback";

    @GetMapping("/login")
    public RedirectView login() throws Exception {
        String targetUrl = "https://accounts.spotify.com/authorize"
                + "?client_id=" + URLEncoder.encode(CLIENT_ID, StandardCharsets.UTF_8.toString())
                + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8.toString())
                + "&response_type=" + URLEncoder.encode("code", StandardCharsets.UTF_8.toString())
                + "&scope=" + URLEncoder.encode("streaming user-read-email user-read-private", StandardCharsets.UTF_8.toString())
                + "&state=" + URLEncoder.encode(generateState(), StandardCharsets.UTF_8.toString());

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(targetUrl);
        return redirectView;
    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("code", code);
        requestBody.add("redirect_uri", REDIRECT_URI);
        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("client_secret", CLIENT_SECRET);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = createRestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://accounts.spotify.com/api/token ",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException();
        }
    }

    private String generateState() {
        return "could-be-more-random-than-this";
    }

    private RestTemplate createRestTemplate() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8888));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);

        return new RestTemplate(requestFactory);
    }
}
