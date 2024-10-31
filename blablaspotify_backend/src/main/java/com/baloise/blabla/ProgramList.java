package com.baloise.blabla;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramList {
    private String channel;
    private String businessUnit;
    private List<Program> programs;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Program {
        private String title;
        private String shortDescription;
        private ProgramDateTimes dateTimes;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ProgramDateTimes {
        private String startTime;
        private String endTime;
        private String duration;
        private String timezone;
    }
}
