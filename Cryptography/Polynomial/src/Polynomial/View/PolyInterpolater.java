package Polynomial.View;

import Polynomial.Polynomial;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;
import org.apache.commons.math.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math.analysis.solvers.NewtonSolver;

import java.math.BigInteger;

/**
 * Created by billy on 2017-07-25.
 */
public class PolyInterpolater extends VBox {
    private int degree;
    private VBox points;
    private TextField x[];
    private TextField y[];
    public PolyInterpolater(){
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(20));
        setSpacing(20);

        Label title = new Label("Polynomial Interpolater");
        title.setFont(Font.font(24));


        getChildren().addAll(title, new Separator());
        setContainer();
    }
    private void setContainer()
    {
        points = new VBox();
        Button interpolateBtn = new Button("Interpolate");
        NumericTextField numericTextField = new NumericTextField();
        getChildren().add(numericTextField);
        numericTextField.setOnKeyReleased(event -> {
            points.getChildren().clear();
            getChildren().removeAll(points, interpolateBtn);
            if(!numericTextField.getText().equals("") && !numericTextField.getText().contains("-")) {
                points = update(Integer.parseInt(numericTextField.getText()));
                getChildren().addAll(points, interpolateBtn);
            }
        });

        interpolateBtn.setOnAction(event -> {
            onInterpolateClick();
        });

    }
    private VBox update(int degree){
        VBox container = new VBox(10);
        this.degree = degree;
        x = new TextField[degree+1];
        y = new TextField[degree+1];
        for(int i = 0; i < degree+1; i++){
            HBox record = new HBox(10);
            record.setAlignment(Pos.CENTER);

            Label noOfPointslbl = new Label("P"+ (i+1));

            x[i] = new TextField();
            x[i].setMaxWidth(110);
            y[i] = new TextField();
            y[i].setMaxWidth(110);
            if(i==0){
                record.getChildren().addAll(new VBox(20,new Label("No."),noOfPointslbl), new VBox(20,new Label("X"),x[i]), new VBox(20,new Label("Y"),y[i]));
            }else {

                record.getChildren().addAll(noOfPointslbl, x[i], y[i]);
            }
            container.getChildren().add(record);
        }
        return container;
    }

    private void onInterpolateClick() {
        Polynomial polynomial  = Polynomial.rand();
        //polyViewer.setPolynomial(polynomial);
        double xPoints[] = new double[degree+1];
        double yPoints[] = new double[degree+1];
        for (int i = 0; i < degree+1; i++) {
            xPoints[i] = Double.parseDouble(x[i].getText());
            yPoints[i] = Double.parseDouble(y[i].getText());
        }

        int length;
        PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm = new PolynomialFunctionLagrangeForm(xPoints,yPoints);
        length = polynomialFunctionLagrangeForm.getCoefficients().length;
        BigInteger[] coefficients = new BigInteger[length];
        for (int i = length - 1; i >=0 ; i--) {
            System.out.println(polynomialFunctionLagrangeForm.getCoefficients()[i]);
            coefficients[i] = new BigInteger(""+(int)polynomialFunctionLagrangeForm.getCoefficients()[length - 1 - i]);
        }
        Polynomial p = new Polynomial(coefficients);
        System.out.println(p.toString());
        PolyEvaluator.p(p);
    }
}
