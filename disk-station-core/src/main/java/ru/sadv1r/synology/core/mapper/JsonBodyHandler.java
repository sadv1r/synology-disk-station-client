package ru.sadv1r.synology.core.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.util.function.Supplier;

/**
 * @see <a href="https://bugs.openjdk.java.net/browse/JDK-8217264">JDK-8217264</a>
 */
public class JsonBodyHandler<T> implements HttpResponse.BodyHandler<Supplier<T>> {

    private final ResponseMapper responseMapper;
    private final Class<T> type;

    public JsonBodyHandler(Class<T> type) {
        this(type, new JacksonResponseMapper());
    }

    public JsonBodyHandler(Class<T> type, ResponseMapper responseMapper) {
        this.type = type;
        this.responseMapper = responseMapper;
    }

    public static <T> JsonBodyHandler<T> ofType(Class<T> type) {
        return new JsonBodyHandler<>(type);
    }

    @Override
    public HttpResponse.BodySubscriber<Supplier<T>> apply(HttpResponse.ResponseInfo responseInfo) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();

        return HttpResponse.BodySubscribers.mapping(upstream, this::toSupplierOfType);
    }

    private Supplier<T> toSupplierOfType(InputStream inputStream) {
        return () -> {
            try (InputStream stream = inputStream) {
                return responseMapper.map(stream, type);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

}
