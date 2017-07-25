package Polynomial;

import Polynomial.Polynomial;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by PC - MeiR on 7/25/2017.
 */
public  class PolynomialOperation {



    public Polynomial addition(Polynomial poly1 , Polynomial poly2){
        if (poly1 == null || poly2 ==null)
            return (poly1==null)? poly2 : poly1;
        BigInteger[] coefficients= new BigInteger[(poly1.getDegree()>=poly2.getDegree())? poly1.getDegree()+1:poly2.getDegree()+1 ];
        int degreeDiff = (poly1.getDegree()>=poly2.getDegree())? poly1.getDegree()-poly2.getDegree():poly2.getDegree()-poly1.getDegree() ;
        for (int i=0;i<degreeDiff;i++){
            coefficients[i]=BigInteger.valueOf(( (poly1.getDegree()>poly2.getDegree())? poly1.getCoefficients()[i]: poly2.getCoefficients()[i] ).intValue());
        }
        int start = 0 ;
        for (int i=degreeDiff; i <= ((poly1.getDegree()>=poly2.getDegree())? poly1.getDegree():poly2.getDegree());i++){

            if (poly1.getDegree()>=poly2.getDegree())
            coefficients[i]=BigInteger.valueOf(poly1.getCoefficients()[i].add(poly2.getCoefficients()[start]).intValue());
            else
                coefficients[i]=BigInteger.valueOf(poly1.getCoefficients()[start].add(poly2.getCoefficients()[i]).intValue());
            start++;
        }


        return new Polynomial(coefficients);
    }



public Polynomial multiplication(Polynomial poly1 , Polynomial poly2){
        Polynomial result=null;
    BigInteger[] coefficients;
    int dgree =0 ;
        for (int i = poly2.getDegree(); i>=0 ;i--){
            // the coefficient of multipli term from poly2  and all poly1
        coefficients=new BigInteger[dgree+poly1.getDegree()+1];
        for (int set=0;set<coefficients.length;set++)
            coefficients[set]=BigInteger.valueOf(0);
        for (int j =0;j<=poly1.getDegree();j++)
            coefficients[j]=BigInteger.valueOf(poly1.getCoefficients()[j].multiply(poly2.getCoefficients()[i]).intValue());

        result=addition(result,new Polynomial(coefficients));
dgree++;
        }



        return result;
}










}
