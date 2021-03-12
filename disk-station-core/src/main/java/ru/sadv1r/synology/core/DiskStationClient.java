package ru.sadv1r.synology.core;

import lombok.SneakyThrows;
import ru.sadv1r.synology.core.exception.DefaultDiskStationExceptionMapper;
import ru.sadv1r.synology.core.exception.DiskStationExceptionMapper;
import ru.sadv1r.synology.core.mapper.JsonBodyHandler;
import ru.sadv1r.synology.core.model.AuthResponse;
import ru.sadv1r.synology.core.model.DiskStationResponse;
import ru.sadv1r.synology.core.model.InfoResponse;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.CookieManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class DiskStationClient {

    private final String serverUrl;
    private final HttpClient httpClient;
    private final DiskStationExceptionMapper exceptionMapper;

    public DiskStationClient() {
        this.serverUrl = "http://nas.local:5000/webapi/"; //TODO Remove hardcode
        this.httpClient = HttpClient.newBuilder()
                .cookieHandler(new CookieManager())
                .build();
        this.exceptionMapper = new DefaultDiskStationExceptionMapper();
    }

    public InfoResponse info() {
        return info(null);
    }

    public InfoResponse info(List<String> apiNames) {
        String query;
        if (apiNames == null) {
            query = "ALL";
        } else {
            query = String.join(",", apiNames);
        }

        DiskStationRequest request = DiskStationRequest.builder()
                .cgiName("query")
                .apiName("SYNO.API.Info")
                .version(1)
                .method("query")
                .param("query", query)
                .build();

        return send(request, InfoResponse.class);
    }

    public void login(String account, String passwd) {
        DiskStationRequest request = DiskStationRequest.builder()
                .cgiName("auth")
                .apiName("SYNO.API.Auth")
                .version(2)
                .method("login")
                .param("account", account)
                .param("passwd", passwd)
                .param("session", "DownloadStation")
                .build();

        send(request, AuthResponse.class);
    }

    public void logout() {
        DiskStationRequest request = DiskStationRequest.builder()
                .cgiName("auth")
                .apiName("SYNO.API.Auth")
                .version(1)
                .method("logout")
                .param("session", "DownloadStation")
                .build();

        send(request, DiskStationResponse.class);
    }

    @SneakyThrows //TODO do something with interrupted exception
    public <T extends DiskStationResponse> T send(DiskStationRequest request, Class<T> type) {
        URI uri = getUri(request);

        HttpRequest build = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        /*HttpResponse<String> send = httpClient.send(build, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.statusCode());
        System.out.println(send.body());*/

        HttpResponse<Supplier<T>> httpResponse;
        try {
            httpResponse = httpClient.send(build, JsonBodyHandler.ofType(type));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        T response = httpResponse.body().get();

        exceptionMapper.checkAndThrow(response);

        return response;
    }

    private URI getUri(DiskStationRequest request) {
        StringBuilder str = new StringBuilder(
                serverUrl + request.getCgiName() + ".cgi" +
                        "?api=" + request.getApiName() +
                        "&version=" + request.getVersion() +
                        "&method=" + request.getMethod());

        if (request.getParams() != null) {
            for (Map.Entry<String, Object> param : request.getParams().entrySet()) {
                str.append("&").append(param.getKey()).append("=").append(param.getValue());
            }
        }

        return URI.create(str.toString());
    }

}
