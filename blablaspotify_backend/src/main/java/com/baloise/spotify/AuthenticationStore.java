package com.baloise.spotify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Component
@NoArgsConstructor
@SessionScope
public class AuthenticationStore {

    private String jwtToken = null;

    public String getAccessToken() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jwtToken);
        return jsonNode.get("access_token").asText();
    }
}
