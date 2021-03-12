package ru.sadv1r.synology.core.mapper;

import java.io.InputStream;

public interface ResponseMapper {

    <T> T map(InputStream inputStream, Class<T> type);

}
