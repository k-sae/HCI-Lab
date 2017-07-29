package CircuitEvaluator.Components;

/**
 * Created by kareem on 7/29/17.
 */
public abstract class AndGate<T> extends Gate<T> {
    @Override
    public T computeOutput() {
        return and(getInputWires().get(0), getInputWires().get(1));
    }
    public abstract T and(Wire<T> val1, Wire<T> val2);
}
