package circuit.breaker;

import circuit.breaker.util.JsonMapper;
import org.apache.http.client.fluent.Response;

import java.io.IOException;
import java.util.Optional;

public class SilentResponse {
    private Response execute;

    public SilentResponse(Response execute) {
        this.execute = execute;
    }

    public SilentResponse() {

    }

    public Optional<Response> getResponse() {
        return Optional.ofNullable(execute);
    }

    public <T> Optional<T> getResponse(Class<T> tClass) {
        if (execute == null) return Optional.empty();
        else {
            try {
                return Optional.of(JsonMapper.parse(execute.returnContent().asBytes(), tClass));
            } catch (IOException e) {
                return Optional.empty();
            }
        }
    }
}
