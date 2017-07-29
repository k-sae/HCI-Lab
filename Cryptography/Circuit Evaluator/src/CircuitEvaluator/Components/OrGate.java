package CircuitEvaluator.Components;

/**
 * Created by kareem on 7/29/17.
 */
public abstract class OrGate<T> extends Gate<T> {
    @Override
    public T computeOutput() {
        return null;
    }
    public abstract T or(T val1, T val2);
}
