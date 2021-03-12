package ru.sadv1r.synology.core.exception;

public class ApiDiskStationException extends DiskStationException {

    public ApiDiskStationException() {
        super(102, "The requested API does not exist");
    }

}
