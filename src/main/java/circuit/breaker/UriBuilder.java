package circuit.breaker;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class UriBuilder {
    private URIBuilder uriBuilder;

    public UriBuilder() {
        this.uriBuilder = new URIBuilder();
    }

    public UriBuilder setPath(String path) {
        uriBuilder.setPath(path);
        return this;
    }

    public UriBuilder addParameter(String name, String value) {
        uriBuilder.addParameter(name, value);
        return this;
    }

    public URI build() throws RuntimeException {
        try {
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
