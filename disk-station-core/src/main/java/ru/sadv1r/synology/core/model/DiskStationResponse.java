package ru.sadv1r.synology.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class DiskStationResponse {

    private Boolean success;
    private Error error;

    @Data
    public static class Error {
        private Integer code;
    }

}
