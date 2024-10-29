package com.baloise.blabla;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StationMetaData(
    @JsonProperty("id") String id,
    @JsonProperty("title") String title,
    @JsonProperty("imageUrl") String imageUrl,
    @JsonProperty("imageTitle") String imageTitle,
    @JsonProperty("transmission") String transmission
) {}
