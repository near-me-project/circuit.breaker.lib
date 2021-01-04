package circuit.breaker.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T parse(byte[] body, Class<T> clazz) {
        try {
            return mapper.readValue(body, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parseAsList(byte[] body, Class<T> clazz) {
        try {
            return mapper.readValue(body, new TypeReference<List<T>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] parse(Object object) {
        try {
            return mapper.writeValueAsString(object).getBytes(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String parseAsString(Object dto) {
        try {
            return mapper.writeValueAsString(dto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}