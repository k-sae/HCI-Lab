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
        gates.get(0).computeOutput();
        return null;
    }

    
}
