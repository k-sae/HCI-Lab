package Polynomial.View;

import Polynomial.Polynomial;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;


import java.awt.image.BufferedImage;

/**
 * Created by kemo on 24/07/2017.
 */
public class PolyViewer extends HBox {
    private Polynomial polynomial;
    private Label basicPolynomialViewer;
    private ImageView equation;
    public PolyViewer()
    {
        basicPolynomialViewer = new Label();
        VBox vBox = new VBox();
//        vBox.setPadding(new Insets(20));
        //TODO
        //1-add textBox for x
        //2-add Evaluate button ( dont add listeners i will add them)
        //3-do some tweaks (padding width whatever
        equation = new ImageView();
        vBox.getChildren().addAll(basicPolynomialViewer,equation );
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
        TeXFormula tex = new TeXFormula(polynomial.toString());
        java.awt.Image awtImage = tex.createBufferedImage(TeXConstants.STYLE_TEXT, 12, java.awt.Color.BLACK, null);
        Image fxImage = SwingFXUtils.toFXImage((BufferedImage) awtImage, null);
         equation.setImage(fxImage);
    }

}
