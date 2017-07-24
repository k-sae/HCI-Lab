package Polynomial;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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

//    public static PolynomialView GetInstance() {
//        if (instance==null){
//
//           instance= new PolynomialView(){};
//        }
//        return instance;
//    }
    public PolynomialView(){
        xAxis=new NumberAxis();
        yAxis =new NumberAxis();
        chart =new LineChart<Number, Number>(xAxis,yAxis);


    }



    @Override
    public void run() {
generateLine();
drawPolynomial();
onDraw(this.chart);

    }




    private void generateLine(){
        line =new XYChart.Series<>();
        for (double x = -100.0;x<=100.0;x+=1.0){
            // TODO replace y with this.polynomial.evaluate(x)

            this.line.getData().add(new XYChart.Data<>(x,x));
        }

    }
    private void drawPolynomial(){
        this.chart.getData().add(this.line);
    }
    public void setPolynomial(Polynomial polynomial){
        this.polynomial = polynomial;
    }



public abstract void onDraw(LineChart<Number,Number> chart);







}
