package ru.sadv1r.synology.downloadstation;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ru.sadv1r.synology.core.DiskStationClient;
import ru.sadv1r.synology.downloadstation.model.TaskListResponse;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
//TODO Подготовка данных для тестирования и нормальные проверки
public class DownloadStationClientTest {

    private static DiskStationClient diskStationClient;
    private DownloadStationClient downloadStationClient;

    @BeforeClass
    public static void beforeClass() {
        diskStationClient = new DiskStationClient();
        diskStationClient.login(System.getProperty("synology-test-user"), System.getProperty("synology-test-password"));
    }

    @Before
    public void before() {
        downloadStationClient = new DownloadStationClient(diskStationClient);
    }

    @Test
    public void list() {
        TaskListResponse info = downloadStationClient.list();
        assertThat(info.getSuccess()).isTrue();
        assertThat(info.getError()).isNull();
        assertThat(info.getData()).isNotNull();
        assertThat(info.getData().getTotal()).isPositive();
        assertThat(info.getData().getOffset()).isZero();
        assertThat(info.getData().getTasks()).isNotEmpty()
                .allSatisfy((task) -> {
                    assertThat(task).isNotNull();
                    assertThat(task.getId()).isNotNull();
                });
    }

    @Test
    public void create() {
        downloadStationClient.create("magnet:?xt=urn:btih:7BAF5525D7F339658754C64D1572920A5FAC5E28&tr=http%3A%2F%2Fbt4.t-ru.org%2Fann%3Fmagnet&dn=%D0%9F%D0%BB%D0%BE%D1%85%D0%B8%D0%B5%20%D0%BF%D0%B0%D1%80%D0%BD%D0%B8%20%D0%BD%D0%B0%D0%B2%D1%81%D0%B5%D0%B3%D0%B4%D0%B0%20%2F%20Bad%20Boys%20for%20Life%20(%D0%90%D0%B4%D0%B8%D0%BB%D1%8C%20%D0%AD%D0%BB%D1%8C%20%D0%90%D1%80%D0%B1%D0%B8%2C%20%D0%91%D0%B8%D0%BB%D0%B0%D0%BB%20%D0%A4%D0%B0%D0%BB%D0%BB%D0%B0%20%2F%20Adil%20El%20Arbi%2C%20Bilall%20Fallah)%20%5B2020%2C%20%D0%A1%D0%A8%D0%90%2C%20%D0%9C%D0%B5%D0%BA%D1%81%D0%B8%D0%BA%D0%B0%2C%20%D0%B1%D0%BE%D0%B5%D0%B2%D0%B8%D0%BA%2C%20%D1%82%D1%80%D0%B8%D0%BB%D0%BB%D0%B5%D1%80%2C%20%D0%BA%D0%BE%D0%BC%D0%B5%D0%B4%D0%B8%D1%8F%2C%20%D0%BA");
    }

    @Test
    public void pause() {
        downloadStationClient.pause("dbid_39");
    }
}