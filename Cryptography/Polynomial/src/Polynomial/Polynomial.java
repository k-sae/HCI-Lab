package Polynomial;

import java.math.BigInteger;
import java.util.Random;

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
        Random rand = new Random();
        int deg=rand.nextInt(5) + 1;
        BigInteger[] randcoefficients=new BigInteger[deg+1];
        for(int i=0;i<deg+1;i++){
           randcoefficients[i]=BigInteger.valueOf(rand.nextInt(1000) + 1);
        }
        Polynomial randp=new Polynomial(randcoefficients);
        return randp;
    }
    //TODO
    public BigInteger evaluate(BigInteger x){

        return null;
    }

    //TODO
    @Override
    public String toString() {
        return super.toString();
    }
}
