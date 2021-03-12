package ru.sadv1r.synology.core.exception.auth;

import ru.sadv1r.synology.core.exception.DiskStationException;

public class SecondStepVerificationAuthDiskStationException extends DiskStationException {

    public SecondStepVerificationAuthDiskStationException() {
        super(404, "Failed to authenticate 2-step verification code");
    }

}
