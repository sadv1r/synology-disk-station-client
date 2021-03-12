package ru.sadv1r.synology.core.exception.auth;

import ru.sadv1r.synology.core.exception.DiskStationException;

public class IncorrectCredentialsAuthDiskStationException extends DiskStationException {

    public IncorrectCredentialsAuthDiskStationException() {
        super(400, "No such account or incorrect password");
    }

}
