package com.baloise.spotify.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistList {

    private String href;
    private int limit;
    private String next;
    private int offset;
    private String previous;
    private int total;
    private List<Item> items;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private boolean collaborative;
        private String description;

        private String href;
        private String id;
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
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner {
        private String href;
        private String id;
        private String type;
        private String uri;

        @JsonProperty("display_name")
        private String displayName;
    }

    @Data
    public static class Tracks {
        private String href;
        private int total;
    }
}
