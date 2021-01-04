package circuit.breaker;

import circuit.breaker.util.JsonMapper;
import org.apache.http.client.fluent.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SilentResponse {
    private Response response;

    public SilentResponse(Response response) {
        this.response = response;
    }

    public SilentResponse() {

    }

    public Optional<Integer> getStatusCode() {
        return Optional.ofNullable(response).map(this::getStatusCode);
    }

    private Integer getStatusCode(Response r) {
        try {
            return r.returnResponse().getStatusLine().getStatusCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Optional<T> getContentAs(Class<T> tClass) {
        if (response == null) return Optional.empty();
        else {
            try {
                return Optional.of(JsonMapper.parse(response.returnContent().asBytes(), tClass));
            } catch (Exception e) {
                System.out.println("[CIRCUIT.BREAKER] [ERROR] " + e.getMessage());
                return Optional.empty();
            }
        }
    }

    public <T> Optional<List<T>> getContentAsList(Class<T> tClass) {
        if (response == null) return Optional.empty();
        else {
            try {
                return Optional.of(JsonMapper.parseAsList(response.returnContent().asBytes(), tClass));
            } catch (Exception e) {
                System.out.println("[CIRCUIT.BREAKER] [ERROR] " + e.getMessage());
                return Optional.empty();
            }
        }
    }

    public Optional<String> getContentAsString() {
        if (response == null) return Optional.empty();
        else {
            try {
                return Optional.of(new String(response.returnContent().asBytes(), "UTF-8"));
            } catch (Exception e) {
                System.out.println("[CIRCUIT.BREAKER] [ERROR] " + e.getMessage());
                return Optional.empty();
            }
        }
    }
}
