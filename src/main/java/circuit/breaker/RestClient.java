package circuit.breaker;

import circuit.breaker.util.JsonMapper;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.util.concurrent.CompletableFuture;

public class RestClient {
    public chain $ = new chain();

    public SilentRequest GET(UriBuilder uriBuilder) {
        return new SilentRequest(Request.Get(uriBuilder.build()));
    }

    public SilentRequest GET(String uri) {
        return new SilentRequest(Request.Get(uri));
    }

    public class chain {

        public CompletableFuture<SilentResponse> GET(String uri) {
            return CompletableFuture.supplyAsync(() -> new SilentRequest(Request.Get(uri)).executeSilent());
        }

        public chain POST(String uri, Object dto) {
            try {
                Request
                        .Post(uri)
                        .bodyString(JsonMapper.parseAsString(dto), ContentType.APPLICATION_JSON)
                        .connectTimeout(500)
                        .execute();
            } catch (Exception e) {
                System.out.println("Failed to make request: " + e.getMessage());
            }
            return this;
        }
    }
}
