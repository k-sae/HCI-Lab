package Polynomial.View;

import Polynomial.Polynomial;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.math.BigDecimal;

/**
 * Created by kemo on 24/07/2017.
 */
public class PolyViewer extends HBox {
    private PolynomialView polynomialView;
    private Polynomial polynomial;
    private Label basicPolynomialViewer;
    private Pane polynomialViewPane;
    private LineChart<Number, Number> polynomialChart;
    public PolyViewer()
    {
        polynomialViewPane = new Pane();
        basicPolynomialViewer = new Label();
        VBox vBox = new VBox();
        polynomialChart = new LineChart<Number, Number>(new NumberAxis(),new NumberAxis());
//        vBox.setPadding(new Insets(20));
        //TODO
        //1-add textBox for x
        //2-add Evaluate button ( dont add listeners i will add them)
        //3-do some tweaks (padding width whatever
        polynomialViewPane.getChildren().add(polynomialChart);
        vBox.getChildren().addAll(basicPolynomialViewer,polynomialViewPane);
        getChildren().addAll(vBox);
    }
    public void setPolynomial(Polynomial polynomial)
    {
        this.polynomial = polynomial;

            System.out.println(polynomial.evaluate(new BigDecimal(0).toBigInteger()).doubleValue());


        updateUi();
    }
    private void updateUi()
    {

        basicPolynomialViewer.setText(polynomial.toString());
        polynomialView=new PolynomialView(polynomial) {
            @Override
            public void onDraw(LineChart<Number, Number> chart) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        polynomialViewPane.getChildren().set(0,chart);

                    }
                });
            }
        };
        polynomialView.start();
    }

}
