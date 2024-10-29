package com.baloise.spotify;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Component
@NoArgsConstructor
@SessionScope
public class AuthenticationStore {

    private String accessToken = null;
}
