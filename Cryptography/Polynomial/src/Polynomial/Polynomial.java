package Polynomial;

import java.math.BigInteger;

/**
 * Created by kemo on 24/07/2017.
 */
public class Polynomial {
    private BigInteger[] coefficients;

    public Polynomial(BigInteger[] coefficients){
        this.coefficients = coefficients;
    }

    //TODO
    public static Polynomial rand()
    {
        return null;
    }
    //TODO
    public BigInteger evaluate(BigInteger x){
           BigInteger result = new BigInteger("0");
           for (int i =0 ; i<coefficients.length;i++){
               result= coefficients[i].multiply(x);
           }
        return result;
    }

    //TODO
    @Override
    public String toString() {
        return super.toString();
    }
}
