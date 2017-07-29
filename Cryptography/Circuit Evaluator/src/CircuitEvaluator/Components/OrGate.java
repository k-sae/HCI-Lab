package CircuitEvaluator.Components;

/**
 * Created by kareem on 7/29/17.
 */
public abstract class OrGate<T> extends Gate<T> {
    public OrGate(Wire<T> input1, Wire<T> input2, Wire<T> output)
    {
        getInputWires().add(input1);
        getInputWires().add(input2);
        setOutput(output);
    }
    @Override
    public T computeOutput() {
        return null;
    }
    public abstract T or(T val1, T val2);
}
