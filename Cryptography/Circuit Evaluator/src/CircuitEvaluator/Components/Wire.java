package CircuitEvaluator.Components;

/**
 * Created by kareem on 7/29/17.
 */
public class Wire<T> {

    private Gate<T> inputGate;
    private Gate<T> outputGate;
    //object so whatever the inputGate we can chnage it later
    private T value;


    public Gate<T> getInputGate() {
        return inputGate;
    }

    public void setInputGate(Gate<T> inputGate) {
        this.inputGate = inputGate;
    }

    public Gate<T> getOutputGate() {
        return outputGate;
    }

    public void setOutputGate(Gate<T> outputGate) {
        this.outputGate = outputGate;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }


}
