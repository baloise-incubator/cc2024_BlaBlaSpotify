package com.baloise.spotify.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Play {
    String context_uri;
    boolean play;
}
