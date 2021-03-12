package ru.sadv1r.synology.core.exception;

public class VersionDiskStationException extends DiskStationException {

    public VersionDiskStationException() {
        super(104, "The requested version does not support the functionality");
    }

}
