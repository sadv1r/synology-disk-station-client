package ru.sadv1r.synology.core.exception;

public class PermissionDiskStationException extends DiskStationException {

    public PermissionDiskStationException() {
        super(105, "The logged in session does not have permission");
    }

}
