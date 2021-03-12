package ru.sadv1r.synology.core.exception;

public class SessionTimeoutDiskStationException extends DiskStationException {

    public SessionTimeoutDiskStationException() {
        super(106, "Session timeout");
    }

}
