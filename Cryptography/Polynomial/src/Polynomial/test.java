package Polynomial;

import Polynomial.Polynomial;

import java.math.BigInteger;

/**
 * Created by PC - MeiR on 7/25/2017.
 */
public class test {

    public static void main(String  []args){

       PolynomialOperation polynomialOperation =new PolynomialOperation();
        BigInteger []bigIntegers =new BigInteger[3];
        BigInteger []bigIntegers1 =new BigInteger[2];
        bigIntegers[0]=BigInteger.valueOf(0);
        bigIntegers[1]=BigInteger.valueOf(2);
        bigIntegers[2]=BigInteger.valueOf(5);

        bigIntegers1[0]=BigInteger.valueOf(4);
        bigIntegers1[1]=BigInteger.valueOf(1);


        Polynomial p1 = new Polynomial(bigIntegers);
       Polynomial p2 =new Polynomial(bigIntegers1);
        System.out.println(polynomialOperation.multiplication(p1,p2).toString());
    }
}
