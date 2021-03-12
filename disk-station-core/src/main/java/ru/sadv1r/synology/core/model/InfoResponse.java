package ru.sadv1r.synology.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class InfoResponse extends DiskStationResponse {

    private Map<String, ResponseData> data;

    @Data
    public static class ResponseData {
        private String path;
        private Integer minVersion;
        private Integer maxVersion;
        private String requestFormat;
    }

}
