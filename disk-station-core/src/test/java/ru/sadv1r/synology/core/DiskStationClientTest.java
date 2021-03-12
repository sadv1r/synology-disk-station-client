package ru.sadv1r.synology.core;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.sadv1r.synology.core.exception.auth.IncorrectCredentialsAuthDiskStationException;
import ru.sadv1r.synology.core.model.InfoResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Ignore
public class DiskStationClientTest {

    private DiskStationClient diskStationClient;

    @Before
    public void before() {
        diskStationClient = new DiskStationClient();
    }

    @Test
    public void info() {
        InfoResponse info = diskStationClient.info();
        assertThat(info.getSuccess()).isTrue();
        assertThat(info.getError()).isNull();
        assertThat(info.getData())
                .containsKeys("SYNO.API.Info", "SYNO.API.Auth")
                .allSatisfy((key, value) -> {
                    assertThat(value).isNotNull();
                    assertThat(value.getPath()).isNotEmpty();
                    assertThat(value.getMinVersion()).isPositive();
                    assertThat(value.getMaxVersion()).isPositive();
                });
    }

    @Test
    public void login() {
        diskStationClient.login(System.getProperty("synology-test-user"), System.getProperty("synology-test-password"));
    }

    @Test
    public void login_wrongPassword() {
        assertThatThrownBy(() -> diskStationClient.login("user", "wrongPassword"))
                .isInstanceOf(IncorrectCredentialsAuthDiskStationException.class)
                .hasFieldOrPropertyWithValue("code", 400)
                .hasMessage("No such account or incorrect password");
    }

    @Test
    public void logout() {
        diskStationClient.logout();
    }

}