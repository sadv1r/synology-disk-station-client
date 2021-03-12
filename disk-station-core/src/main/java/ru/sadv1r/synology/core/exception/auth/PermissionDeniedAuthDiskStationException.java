package ru.sadv1r.synology.core.exception.auth;

import ru.sadv1r.synology.core.exception.DiskStationException;

public class PermissionDeniedAuthDiskStationException extends DiskStationException {

    public PermissionDeniedAuthDiskStationException() {
        super(402, "Permission denied");
    }

}
