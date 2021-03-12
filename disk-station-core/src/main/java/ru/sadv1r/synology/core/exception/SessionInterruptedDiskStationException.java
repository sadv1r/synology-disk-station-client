package ru.sadv1r.synology.core.exception;

public class SessionInterruptedDiskStationException extends DiskStationException {

    public SessionInterruptedDiskStationException() {
        super(107, "Session interrupted by duplicate login");
    }

}
