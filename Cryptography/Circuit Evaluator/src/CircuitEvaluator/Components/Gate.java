package CircuitEvaluator.Components;

import java.util.ArrayList;

/**
 * Created by kareem on 7/29/17.
 */
public abstract class Gate<T> {
    private Wire<T> output;
    //ArrayList Bec some gates contain one and others two wires and maybe even more
    private ArrayList<Wire<T>> inputWires=new ArrayList<Wire<T>>();

    public abstract T computeOutput();

    public Wire<T> getOutput() {
        return output;
    }

    public void setOutput(Wire<T> output) {
        this.output = output;
    }
    public ArrayList<Wire<T>> getInputWires() {
        return inputWires;
    }

}
