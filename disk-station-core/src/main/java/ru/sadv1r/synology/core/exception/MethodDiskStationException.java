package ru.sadv1r.synology.core.exception;

public class MethodDiskStationException extends DiskStationException {

    public MethodDiskStationException() {
        super(103, "The requested method does not exist");
    }

}
