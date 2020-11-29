package circuit.breaker;

@FunctionalInterface
public interface Action {
    void act();
}
