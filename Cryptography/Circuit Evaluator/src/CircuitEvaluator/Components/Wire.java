package CircuitEvaluator.Components;

/**
 * Created by kareem on 7/29/17.
 */
public class Wire<T> {

    private Gate<T> input;
    private Gate<T> output;
    //object so whatever the input we can chnage it later
    private T value;


    public Gate<T> getInput() {
        return input;
    }

    public void setInput(Gate<T> input) {
        this.input = input;
    }

    public Gate<T> getOutput() {
        return output;
    }

    public void setOutput(Gate<T> output) {
        this.output = output;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }


}
