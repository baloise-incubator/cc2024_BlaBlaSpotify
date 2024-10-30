package com.baloise.spotify.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class User {

    private String country;

    @JsonProperty("display_name")
    private String displayName;

    private String email;

    @JsonProperty("explicit_content")
    private ExplicitContent explicitContent;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    private Followers followers;
    private String href;
    private String id;
    private List<Image> images;
    private String product;
    private String type;
    private String uri;

    @Data
    public static class ExplicitContent {
        @JsonProperty("filter_enabled")
        private boolean filterEnabled;

        @JsonProperty("filter_locked")
        private boolean filterLocked;
    }

    @Data
    public static class ExternalUrls {
        private String spotify;
    }

    @Data
    public static class Followers {
        private String href;
        private int total;
    }

    @Data
    public static class Image {
        private String url;
        private int height;
        private int width;
    }
}
