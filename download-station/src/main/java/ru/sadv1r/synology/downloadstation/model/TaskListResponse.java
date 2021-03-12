package ru.sadv1r.synology.downloadstation.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.sadv1r.synology.core.model.DiskStationResponse;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class TaskListResponse extends DiskStationResponse {

    private ResponseData data;

    @Data
    public static class ResponseData {
        private Integer total;
        private Integer offset;
        private List<Task> tasks;

        @Data
        public static class Task {
            private String id;
            private String type;
            private String username;
            private String title;
            private Long size; //TODO нормальный формат
            private String status;
            private String statusExtra;
            private Additional additional;

            @Data
            public static class Additional {
                //TODO нормальные модели
                private Map<String, Object> detail;
                private Map<String, Object> transfer;
                private Map<String, Object> file;
                private Map<String, Object> tracker;
                private Map<String, Object> peer;
            }
        }
    }

}
