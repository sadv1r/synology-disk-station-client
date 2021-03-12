package ru.sadv1r.synology.bundle;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import ru.sadv1r.synology.core.DiskStationClient;
import ru.sadv1r.synology.downloadstation.DownloadStationClient;

@RequiredArgsConstructor
public class DiskStationBundleClient {

    @Delegate
    private final DiskStationClient diskStationClient;

    public DownloadStationClient downloadStation() {
        return new DownloadStationClient(diskStationClient);
    }

}
