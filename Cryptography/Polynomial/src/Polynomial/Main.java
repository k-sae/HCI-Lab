package Polynomial;

import Polynomial.View.MainWindow;
import Polynomial.View.PolynomialView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;

import javafx.scene.image.Image;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;
import org.apache.commons.math.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math.analysis.solvers.NewtonSolver;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = new MainWindow();
        primaryStage.setTitle("Polynomial Evaluator");
        primaryStage.getIcons().add(new Image("file:img/icon.jpg"));
        primaryStage.setScene(new Scene(root, 1280,720 ));
        double x[] = { 2,-2};
        double y[] = { 8,-4};
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm  = new PolynomialFunctionLagrangeForm(x,y);
        int coefficients_size = polynomialFunctionLagrangeForm.getCoefficients().length ;
        double[] coefficients_Double = polynomialFunctionLagrangeForm.getCoefficients();
        BigInteger [] coefficients = new BigInteger [coefficients_size];
        System.out.println("coefficients:");
        for (int i =coefficients_size-1; i >=0 ; i--) {
            System.out.println(coefficients_Double[i]);

        }
      // lop for  cast  double to Big integer
        for (int i = 0 ; i< coefficients_size;i++) {
            long c = (long) coefficients_Double[coefficients_size - i-1];
            coefficients[i] = BigInteger.valueOf(c);
        }

       Polynomial polynomial = new Polynomial(coefficients);
        System.out.println(polynomial.toString());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
