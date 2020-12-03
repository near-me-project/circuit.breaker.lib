package circuit.breaker;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import java.io.IOException;

public class SilentRequest {
    private Request request;

    public SilentRequest(Request request) {
        this.request = request;
    }

    public Response execute() throws IOException {
        return request.execute();
    }

    public SilentResponse executeSilent()  {
        try {
            return new SilentResponse(request.execute());
        } catch (Exception e) {
            System.out.println(" >>>>>> ERROR: " + e.getMessage());
            return new SilentResponse();
        }
    }
}
