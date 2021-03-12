package ru.sadv1r.synology.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthResponse extends DiskStationResponse {

    private ResponseData data;

    @Data
    public static class ResponseData {
        private String sid;
    }

}
