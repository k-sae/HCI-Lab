package CircuitEvaluator.Components;

import java.util.ArrayList;

/**
 * Created by kareem on 7/29/17.
 */
public abstract class Gate<T> {
    private Wire output;
    //ArrayList Bec some gates contain one and others two wires and maybe even more
    private ArrayList<Wire> inputWires;

    public abstract T computeOutput();

    public Wire getOutput() {
        return output;
    }

    public void setOutput(Wire output) {
        this.output = output;
    }
    public ArrayList<Wire> getInputWires() {
        return inputWires;
    }

}
