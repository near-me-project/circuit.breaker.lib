import circuit.breaker.RestClient;
import circuit.breaker.UriBuilder;
import org.apache.http.client.fluent.Response;

public class Tests {
    public void t1() {
        Response response = new RestClient()
                .GET(new UriBuilder().setPath("http://localhost:8080/discovery/").addParameter("client", "info-ws"))
                .executeSilent()
                .getResponse()
                .orElseThrow(RuntimeException::new);
    }
}
