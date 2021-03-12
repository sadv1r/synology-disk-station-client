package ru.sadv1r.synology.downloadstation;

import lombok.RequiredArgsConstructor;
import ru.sadv1r.synology.core.DiskStationClient;
import ru.sadv1r.synology.core.DiskStationRequest;
import ru.sadv1r.synology.core.model.DiskStationResponse;
import ru.sadv1r.synology.downloadstation.model.TaskListResponse;

@RequiredArgsConstructor
public class DownloadStationClient {

    private final DiskStationClient client;

    public DiskStationResponse info() {
        DiskStationRequest request = DiskStationRequest.builder()
                .cgiName("DownloadStation/info")
                .apiName("SYNO.DownloadStation.Info")
                .version(1)
                .method("getinfo")
                .build();

        return client.send(request, DiskStationResponse.class);
    }

    public DiskStationResponse config() {
        DiskStationRequest request = DiskStationRequest.builder()
                .cgiName("DownloadStation/info")
                .apiName("SYNO.DownloadStation.Info")
                .version(1)
                .method("getconfig")
                .build();

        return client.send(request, DiskStationResponse.class);
    }

    public TaskListResponse list() {
        DiskStationRequest request = DiskStationRequest.builder()
                .cgiName("DownloadStation/task")
                .apiName("SYNO.DownloadStation.Task")
                .version(1)
                .method("list")
                .build();
        //TODO "&additional=detail" and other params

        return client.send(request, TaskListResponse.class);
    }

    public void create(String uri) {
        DiskStationRequest request = DiskStationRequest.builder()
                .cgiName("DownloadStation/task")
                .apiName("SYNO.DownloadStation.Task")
                .version(1)
                .method("create")
                .param("uri", uri)
                .build();
        //TODO other params

        client.send(request, DiskStationResponse.class);
    }

    public void pause(String id) {
        DiskStationRequest request = DiskStationRequest.builder()
                .cgiName("DownloadStation/task")
                .apiName("SYNO.DownloadStation.Task")
                .version(1)
                .method("pause")
                .param("id", id)
                .build();
        //TODO other params

        client.send(request, DiskStationResponse.class);
    }

}
