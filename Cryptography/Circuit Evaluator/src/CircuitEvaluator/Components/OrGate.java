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
        input1.setOutputGate(this);
        input2.setOutputGate(this);
        output.setInputGate(this);
    }
    @Override
    public T computeOutput() {
        return or(getInputWires().get(0),getInputWires().get(1));
    }
    public abstract T or(Wire<T> val1, Wire<T> val2);
}
