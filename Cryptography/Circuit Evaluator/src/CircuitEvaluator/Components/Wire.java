package CircuitEvaluator.Components;

/**
 * Created by kareem on 7/29/17.
 */
public class Wire<T> {

    private Gate input;
    private Gate output;
    //object so whatever the input we can chnage it later
    private T value;


    public Gate getInput() {
        return input;
    }

    public void setInput(Gate input) {
        this.input = input;
    }

    public Gate getOutput() {
        return output;
    }

    public void setOutput(Gate output) {
        this.output = output;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }


}
