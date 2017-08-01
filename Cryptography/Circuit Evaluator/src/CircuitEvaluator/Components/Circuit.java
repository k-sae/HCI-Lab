package CircuitEvaluator.Components;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by kareem on 7/29/17.
 */
public class Circuit<T> {
    private ArrayList<Gate<T>> gates = new ArrayList<>();

    public ArrayList<Gate<T>> getGates() {
        return gates;
    }
    //TODO
    public T evaluate(){
        Wire start=null;
        for (Gate gate:gates) {
            if (gate.getOutput().getOutputGate()==null)
                start = gate.getOutput();
        }
        computeRecursively(start);

        return (T) start.getValue();
    }

    private void computeRecursively(Wire<T> wire){
        if(wire.getInputGate().getInputWires().get(0)!=null) {
            if (wire.getInputGate().getInputWires().get(0).getInputGate() != null)
                computeRecursively(wire.getInputGate().getInputWires().get(0));
            if (wire.getInputGate().getInputWires().get(1).getInputGate() != null)
                computeRecursively(wire.getInputGate().getInputWires().get(1));
        }
          wire.setValue(wire.getInputGate().computeOutput());
        //wire.setInputGate(null);

    }
}
