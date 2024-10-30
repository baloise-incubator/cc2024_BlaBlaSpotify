package com.baloise.spotify.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistList {

    private String href;
    private int limit;
    private String next;
    private int offset;
    private String previous;
    private int total;
    private List<Item> items;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private boolean collaborative;
        private String description;

        @JsonProperty("external_urls")
        private ExternalUrls externalUrls;

        private String href;
        private String id;
        private List<Image> images;
        private String name;
        private Owner owner;
        private boolean isPublic;

        @JsonProperty("snapshot_id")
        private String snapshotId;

        private Tracks tracks;
        private String type;
        private String uri;
    }

    @Data
    public static class ExternalUrls {
        private String spotify;
    }

    @Data
    public static class Image {
        private String url;
        private int height;
        private int width;
    }

    @Data
    public static class Owner {
        @JsonProperty("external_urls")
        private ExternalUrls externalUrls;

        private Followers followers;
        private String href;
        private String id;
        private String type;
        private String uri;

        @JsonProperty("display_name")
        private String displayName;
    }

    @Data
    public static class Followers {
        private String href;
        private int total;
    }

    @Data
    public static class Tracks {
        private String href;
        private int total;
    }
}
