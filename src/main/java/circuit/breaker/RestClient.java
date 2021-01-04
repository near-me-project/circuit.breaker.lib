package circuit.breaker;

import circuit.breaker.config.ApplicationConfiguration;
import circuit.breaker.util.JsonMapper;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.util.concurrent.CompletableFuture;

public class RestClient {
    private ApplicationConfiguration configuration;
    private int defaultTimeout;

    public Async async = new Async();

    public RestClient() {
        configuration  = new ApplicationConfiguration();
        defaultTimeout = configuration.timeRequest();
    }

    public SilentRequest GET(UriBuilder uriBuilder) {
        return GET(uriBuilder, defaultTimeout);
    }

    public SilentRequest GET(UriBuilder uriBuilder, int timeout) {
        return new SilentRequest(Request.Get(uriBuilder.build()).connectTimeout(timeout));
    }

    public SilentRequest GET(String uri) {
        return GET(uri, defaultTimeout);
    }

    public SilentRequest GET(String uri, int timeout) {
        return new SilentRequest(Request.Get(uri).connectTimeout(timeout));
    }

    public SilentRequest PUT(String uri, Object dto) {
        return PUT(uri, dto, defaultTimeout);
    }

    public SilentRequest PUT(String uri, Object dto, int timeout) {
        return new SilentRequest(Request.Put(uri).bodyString(JsonMapper.parseAsString(dto), ContentType.APPLICATION_JSON).connectTimeout(timeout));
    }

    public SilentRequest POST(String uri, Object dto) {
        return POST(uri, dto, defaultTimeout);
    }

    public SilentRequest POST(String uri, Object dto, int timeout) {
        return new SilentRequest(Request.Post(uri).bodyString(JsonMapper.parseAsString(dto), ContentType.APPLICATION_JSON).connectTimeout(timeout));
    }

    public class Async {

        public CompletableFuture<SilentResponse> GET(String uri) {
            return CompletableFuture.supplyAsync(() -> new SilentRequest(Request.Get(uri)).executeSilent());
        }

        public CompletableFuture<SilentResponse> POST(String uri, Object dto) {
            return CompletableFuture.supplyAsync(() ->
                    new SilentRequest(Request.Post(uri).bodyString(JsonMapper.parseAsString(dto), ContentType.APPLICATION_JSON).connectTimeout(500)).executeSilent());
        }
    }
}
