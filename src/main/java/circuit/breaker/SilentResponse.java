package circuit.breaker;

import circuit.breaker.util.JsonMapper;
import org.apache.http.client.fluent.Response;

import java.io.IOException;
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
            } catch (IOException e) {
                return Optional.empty();
            }
        }
    }
}
