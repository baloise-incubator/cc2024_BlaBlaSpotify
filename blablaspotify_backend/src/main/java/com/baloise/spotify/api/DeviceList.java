package com.baloise.spotify.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class DeviceList {
    private List<Device> devices;

    @Data
    public static class Device {
        private String id;
        private boolean isActive;
        private boolean isPrivateSession;
        private boolean isRestricted;
        private String name;
        private String type;
        private int volumePercent;
        private boolean supportsVolume;
    }
}
