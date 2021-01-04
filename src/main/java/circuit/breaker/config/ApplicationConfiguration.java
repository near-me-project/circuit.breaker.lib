package circuit.breaker.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ApplicationConfiguration {
    private Config config;

    public ApplicationConfiguration() {
        try {
            config = ConfigFactory.load("application.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer timeRequest() {
        return config.getInt("circuit.breaker.default.timeout");
    }
}
