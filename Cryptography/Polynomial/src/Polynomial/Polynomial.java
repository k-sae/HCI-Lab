package Polynomial;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by kemo on 24/07/2017.
 */
public class Polynomial {
    private BigInteger[] coefficients;
    private int degree;

    public Polynomial(BigInteger[] coefficients){
        this.coefficients = coefficients;
        this.degree = this.coefficients.length - 1;
    }

    //TODO
    public static Polynomial rand()
    {
        Random rand = new Random();
        int deg=rand.nextInt(9) + 1;
        BigInteger[] randcoefficients=new BigInteger[deg+1];
        for(int i=0;i<deg+1;i++){
           randcoefficients[i]=BigInteger.valueOf(rand.nextInt(1000));
        }
        Polynomial randp=new Polynomial(randcoefficients);
        return randp;
    }
    //TODO
    public BigInteger evaluate(BigInteger x){

           BigInteger result = new BigInteger("0");
           for (int i = degree ; i>=0;i--){

               result=result.add(coefficients[i].multiply(x.pow(degree-i)));
               System.out.println(result.intValue());
           }
        return result;
    }

    //TODO
    @Override
    public String toString() {
        String formula = "";

        for(int i = 0; i <= this.degree; i++){
            int power = (this.degree - i);

            //If the coeff is 0 pass the loop and don't print it!
            if(coefficients[i].intValue() == 0){

                continue;
            }

            //If this coeff is the first one
            if(formula.equals("")){

                //If the coeff equal 1, don't print it
                if(coefficients[i].abs().intValue() == 1) {

                    //If the coeff is negative print the - sign
                    if(coefficients[i].intValue() < 0){

                        formula += "-";
                    }
                //If the coeff not equal 1, print it
                }else
                    formula += coefficients[i];
            }else{

                //If the coeff is positive print the + sign
                if(coefficients[i].intValue() > 0){

                    formula += " + ";
                //If the coeff is negative print the - sign
                }else {

                    formula += " - ";
                }
                //If the coeff is not equal 1, print it, else don't print 1, except if the power equal 0 -The last coeff-
                if(coefficients[i].abs().intValue() != 1 || power == 0)
                    formula += coefficients[i].abs();
            }

            //If the power greater than 0 print X without the power
            if(power > 0){

                formula += "X";
            }
            //If the power greater than 1 print the power
            if(power > 1){

                formula += "^" + power;
            }
        }

        return formula;
    }
}
