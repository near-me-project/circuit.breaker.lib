import circuit.breaker.config.ApplicationConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void f(){
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
        Assertions.assertEquals(500, applicationConfiguration.timeRequest());
    }
}
