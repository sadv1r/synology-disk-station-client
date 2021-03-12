package ru.sadv1r.synology.core.exception;

public class ParameterDiskStationException extends DiskStationException {

    public ParameterDiskStationException() {
        super(101, "Invalid parameter");
    }

}
