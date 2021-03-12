package ru.sadv1r.synology.core.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

public class JacksonResponseMapper implements ResponseMapper {

    private final ObjectMapper objectMapper;

    public JacksonResponseMapper() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> T map(InputStream inputStream, Class<T> type) {
        try {
            return objectMapper.readValue(inputStream, type);
//            JsonNode jsonNode = objectMapper.readTree(inputStream);
//            JsonNode data = jsonNode.get("data"); //TODO Костыль поправить
//            return objectMapper.convertValue(data, type);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
