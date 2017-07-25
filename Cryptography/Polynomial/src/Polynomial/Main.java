package Polynomial;

import Polynomial.View.MainWindow;
import Polynomial.View.PolynomialView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;
import org.apache.commons.math.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math.analysis.solvers.NewtonSolver;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Hello World");

        Parent root = new MainWindow();
        primaryStage.setTitle("Polynomial Evaluator");
        primaryStage.setScene(new Scene(root, 1280,720 ));
        double x[] = { -1.0, 0.0, 1.0,2.0 };
        double y[] = { 1.0, 1.0, 3.0,1.0};
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm  = new PolynomialFunctionLagrangeForm(x,y);
        System.out.println("coefficients:");
        for (int i = polynomialFunctionLagrangeForm.getCoefficients().length - 1; i >=0 ; i--) {
            System.out.println(polynomialFunctionLagrangeForm.getCoefficients()[i]);
        }

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
