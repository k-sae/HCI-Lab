package Polynomial.View;

import Polynomial.Polynomial;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by PC - MeiR on 7/24/2017.
 */
public abstract   class PolynomialView extends Thread{
    private int width , height ;
    private LineChart<Number,Number> chart ;
    private     NumberAxis xAxis , yAxis ;
   private XYChart.Series<Number,Number> line;
    private Polynomial polynomial;
    private static PolynomialView instance;

    public PolynomialView(Polynomial polynomial){
        xAxis=new NumberAxis();
        yAxis =new NumberAxis();
        line =new XYChart.Series<>();
        this.polynomial=polynomial;


    }



    @Override
    public void run() {

        generateLine();
        drawPolynomial();
        onDraw(this.chart);
        System.out.println(this.line.getData());


    }




    private void generateLine(){

        for (double x = -10.0;x<=10.0;x+=1.0){
            // TODO replace y with this.polynomial.evaluate(x)

            this.line.getData().add(new XYChart.Data<>(x,this.polynomial.evaluate(new BigDecimal(x).toBigInteger()).doubleValue()*0.5));
        }

    }
    private void drawPolynomial(){
        chart =new LineChart<Number, Number>(xAxis,yAxis);
        this.chart.getData().add(this.line);
    }
    public void setPolynomial(Polynomial polynomial){
        this.polynomial = polynomial;
    }



public abstract void onDraw(LineChart<Number,Number> chart);







}
