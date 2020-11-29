package circuit.breaker;

import circuit.breaker.util.JsonMapper;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

public class RestClient {

    public SilentRequest GET(UriBuilder uriBuilder)  {
        return new SilentRequest(Request.Get(uriBuilder.build()));
    }

    public RestClient POST(String uri, Object dto) {
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

    public void ifFail(Action action) {
        try {
            action.act();
        } catch (Exception e) {}
    }
}
