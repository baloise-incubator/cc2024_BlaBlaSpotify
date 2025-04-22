package com.baloise.spotify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@Data
@Component
@NoArgsConstructor
public class AuthenticationStore {

    private String jwtToken = null;

    public void setJwtToken(String jwtToken) {
        log.info("Setting JWT token objectId={}", this.hashCode());
        this.jwtToken = jwtToken;
    }

    public String getAccessToken() throws JsonProcessingException {
        log.info("Getting JWT token objectId={}", this.hashCode());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jwtToken);
        return jsonNode.get("access_token").asText();
    }
}
