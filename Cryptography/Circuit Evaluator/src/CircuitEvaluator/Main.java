package CircuitEvaluator;

import CircuitEvaluator.Components.AndGate;
import CircuitEvaluator.Components.Circuit;
import CircuitEvaluator.Components.OrGate;
import CircuitEvaluator.Components.Wire;

/**
 * Created by kareem on 7/29/17.
 */
public class Main {
    public static void main(String... args)
    {
        Wire wire= new Wire();
        Wire wire1= new Wire();
        Wire wire2= new Wire();
        Wire wire3= new Wire();
        Wire wire4= new Wire();
        Wire wire5= new Wire();
        Wire wire6= new Wire();
        wire.setValue(0);
        wire1.setValue(1);
        wire2.setValue(0);
        wire3.setValue(0);

        AndGate andGate =new AndGate(wire,wire1,wire5) {
            @Override
            public Object and(Wire val1, Wire val2) {
                return ((int)val1.getValue() ==1 || (int)val2.getValue()==1)? 1:0 ;
            }
        };
        AndGate andGate1 =new AndGate(wire2,wire3,wire4) {
            @Override
            public Object and(Wire val1, Wire val2) {
                return ((int)val1.getValue() ==1 || (int)val2.getValue()==1)? 1:0 ;
            }
        };
        OrGate orGate = new OrGate(wire4,wire5,wire6) {
            @Override
            public Object or(Wire val1, Wire val2) {
                return ((int)val1.getValue() ==0 || (int)val2.getValue()==0)? 0:1 ;
            }
        };


        Circuit circuit = new Circuit();

        circuit.getGates().add(andGate);
        circuit.getGates().add(andGate1);
        circuit.getGates().add(orGate);

        System.out.println(circuit.evaluate());



    }

}
