package Polynomial.View;

import Polynomial.Polynomial;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by kemo on 24/07/2017.
 */
public class PolyViewer extends HBox {
    private Polynomial polynomial;
    private Label basicPolynomialViewer;
    public PolyViewer()
    {
        basicPolynomialViewer = new Label();
        VBox vBox = new VBox();
//        vBox.setPadding(new Insets(20));
        //TODO
        //1-add textBox for x
        //2-add Evaluate button ( dont add listeners i will add them)
        //3-do some tweaks (padding width whatever
        vBox.getChildren().addAll(basicPolynomialViewer);
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
    }

}
