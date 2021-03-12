package ru.sadv1r.synology.core;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class DiskStationRequest {

    String cgiName;
    String apiName;
    int version;
    String method;
    @Singular
    Map<String, Object> params;

}
