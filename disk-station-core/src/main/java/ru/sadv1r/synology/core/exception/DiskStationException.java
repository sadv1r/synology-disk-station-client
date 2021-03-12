package ru.sadv1r.synology.core.exception;

import lombok.Getter;

public class DiskStationException extends RuntimeException {

    @Getter
    int code;

    public DiskStationException() {
        this(100, "Unknown error");
    }

    public DiskStationException(int code) {
        this(code, "Unmapped error");
    }

    public DiskStationException(int code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public String toString() {
        String s = this.getClass().getName();
        String message = this.getLocalizedMessage();
        return message != null ? s + ": " + code + " " + message : s;
    }

}
