package Polynomial;

import java.math.BigInteger;

/**
 * Created by mosta on 26-Jul-17.
 */
public class Secret {
    public static Polynomial Secretaddition(int Secert1,int Secert2){
        Polynomial p1=Polynomial.rand(1);
        BigInteger[] coff ;
        coff =p1.getCoefficients();
        coff[1]=BigInteger.valueOf(Secert1);
        p1.Setcofficients(coff);
        Polynomial P2=Polynomial.rand(1);
        coff =P2.getCoefficients();
        coff[1]=BigInteger.valueOf(Secert2);
        P2.Setcofficients(coff);

        return p1;
    }
}
