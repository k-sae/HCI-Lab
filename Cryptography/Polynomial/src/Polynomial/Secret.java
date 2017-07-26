package Polynomial;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.math.BigInteger;

/**
 * Created by mosta on 26-Jul-17.
 */
public class Secret {
    public static Polynomial[] polynimalGenerate(int Secert1,int Secert2){
        Polynomial p[] =new Polynomial[2];
        Polynomial p1=Polynomial.rand(1);
        BigInteger[] coff ;
        coff =p1.getCoefficients();
        coff[1]=BigInteger.valueOf(Secert1);
        p1.Setcofficients(coff);
        p[0]=p1;
        Polynomial P2=Polynomial.rand(1);
        coff =P2.getCoefficients();
        coff[1]=BigInteger.valueOf(Secert2);
        P2.Setcofficients(coff);
        p[1]=P2;
        return p;
    }
    public static BigInteger[] EvaluateMultiplactionPoints (Polynomial[] p,BigInteger btats){
        BigInteger[] coff ;
        BigInteger points[]=new BigInteger[18];
        int k=0;
        for(int i=0;i<5;i++) {
            coff = p[k].getCoefficients();
            points[i] = coff[1];
            points[i+1] = p[0].evaluate(coff[1]);
            points[i+2] = coff[1];
            points[i+3] = p[1].evaluate(coff[1]);
            if(i==0){  i=3;}
            k++;
        }
        points[8]=btats;
        points[9]=p[0].evaluate(btats);
        points[10]=btats;
        points[11]=p[1].evaluate(btats);
        points[12]=points[0];
        points[13]=points[1].multiply(points[3]);
        points[14]=points[4];
        points[15]=points[5].multiply(points[7]);
        points[16]=btats;
        points[17]=points[9].multiply(points[11]);
        return points;

    }
    public static BigInteger[] EvaluatePoints (Polynomial[] p){
        BigInteger[] coff ;
        BigInteger points[]=new BigInteger[12];
        int k=0;
        for(int i=0;i<5;i++) {
            coff = p[k].getCoefficients();
            points[i] = coff[1];
            points[i+1] = p[0].evaluate(coff[1]);
            points[i+2] = coff[1];
            points[i+3] = p[1].evaluate(coff[1]);
          if(i==0){  i=3;}
            k++;
        }
        points[8]=points[0];
        points[9]=points[1].add(points[3]);
        points[10]=points[4];
        points[11]=points[5].add(points[7]);
            return points;
    }
}
