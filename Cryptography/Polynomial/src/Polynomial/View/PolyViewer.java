package Polynomial.View;

import Polynomial.Polynomial;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.awt.image.BufferedImage;

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

    private ImageView equation;

    public PolyViewer()
    {
        polynomialViewPane = new Pane();
        basicPolynomialViewer = new Label();
        basicPolynomialViewer.setFont(Font.font(20));
        VBox vBox = new VBox();
        polynomialChart = new LineChart<Number, Number>(new NumberAxis(),new NumberAxis());
//        vBox.setPadding(new Insets(20));
        //TODO
        //1-add textBox for x
        //2-add Evaluate button ( dont add listeners i will add them)
        //3-do some tweaks (padding width whatever
        polynomialViewPane.getChildren().add(polynomialChart);
        equation = new ImageView();
        vBox.getChildren().addAll(basicPolynomialViewer,equation,polynomialViewPane);
        getChildren().addAll(vBox);
    }
    public void setPolynomial(Polynomial polynomial)
    {
        this.polynomial = polynomial;



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
        TeXFormula tex = new TeXFormula(polynomial.toString());
        java.awt.Image awtImage = tex.createBufferedImage(TeXConstants.STYLE_TEXT, 24, java.awt.Color.BLACK, null);
        Image fxImage = SwingFXUtils.toFXImage((BufferedImage) awtImage, null);
         equation.setImage(fxImage);
    }

}
