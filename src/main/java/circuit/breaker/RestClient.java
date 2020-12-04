package circuit.breaker;

import circuit.breaker.util.JsonMapper;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.util.concurrent.CompletableFuture;

public class RestClient {
    public Async async = new Async();

    public SilentRequest GET(UriBuilder uriBuilder) {
        return new SilentRequest(Request.Get(uriBuilder.build()));
    }

    public SilentRequest GET(String uri) {
        return new SilentRequest(Request.Get(uri));
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
