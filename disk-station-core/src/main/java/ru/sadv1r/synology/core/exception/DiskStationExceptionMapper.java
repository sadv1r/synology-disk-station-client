package ru.sadv1r.synology.core.exception;

import ru.sadv1r.synology.core.exception.auth.*;
import ru.sadv1r.synology.core.model.DiskStationResponse;

public abstract class DiskStationExceptionMapper {

    abstract DiskStationException map(DiskStationResponse response);

    public void checkAndThrow(DiskStationResponse response) {
        if (response.getSuccess()) {
            return;
        }

        DiskStationException exception = map(response);
        if (exception != null) {
            throw exception;
        }

        throw mapDefaultException(response);
    }

    //FIXME remove switch. Need to use code from exception classes
    private DiskStationException mapDefaultException(DiskStationResponse diskStationResponse) {
        switch (diskStationResponse.getError().getCode()) {
            case 101:
                return new ParameterDiskStationException();
            case 102:
                return new ApiDiskStationException();
            case 103:
                return new MethodDiskStationException();
            case 104:
                return new VersionDiskStationException();
            case 105:
                return new PermissionDiskStationException();
            case 106:
                return new SessionTimeoutDiskStationException();
            case 107:
                return new SessionInterruptedDiskStationException();
            case 400:
                return new IncorrectCredentialsAuthDiskStationException();
            case 401:
                return new AccountDisabledAuthDiskStationException();
            case 402:
                return new PermissionDeniedAuthDiskStationException();
            case 403:
                return new SecondStepRequiredAuthDiskStationException();
            case 404:
                return new SecondStepVerificationAuthDiskStationException();
            default:
                return new DiskStationException(diskStationResponse.getError().getCode());
        }
    }

}
