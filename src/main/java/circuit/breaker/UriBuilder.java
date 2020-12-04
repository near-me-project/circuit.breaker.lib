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
        URI uri = URI.create(path);
        uriBuilder.setScheme(uri.getScheme());
        uriBuilder.setHost(uri.getHost());
        uriBuilder.setPort(uri.getPort());
        uriBuilder.setPath(uri.getPath());
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
