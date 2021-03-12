package ru.sadv1r.synology.core.exception.auth;

import ru.sadv1r.synology.core.exception.DiskStationException;

public class AccountDisabledAuthDiskStationException extends DiskStationException {

    public AccountDisabledAuthDiskStationException() {
        super(401, "Account disabled");
    }

}
