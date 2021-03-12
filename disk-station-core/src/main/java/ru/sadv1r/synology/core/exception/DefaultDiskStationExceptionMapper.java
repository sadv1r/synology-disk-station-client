package ru.sadv1r.synology.core.exception;

import ru.sadv1r.synology.core.model.DiskStationResponse;

public class DefaultDiskStationExceptionMapper extends DiskStationExceptionMapper {

    @Override
    public DiskStationException map(DiskStationResponse response) {
        return null;
    }

}
