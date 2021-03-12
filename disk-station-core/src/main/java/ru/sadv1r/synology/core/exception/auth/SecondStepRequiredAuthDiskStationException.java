package ru.sadv1r.synology.core.exception.auth;

import ru.sadv1r.synology.core.exception.DiskStationException;

public class SecondStepRequiredAuthDiskStationException extends DiskStationException {

    public SecondStepRequiredAuthDiskStationException() {
        super(403, "2-step verification code required");
    }

}
