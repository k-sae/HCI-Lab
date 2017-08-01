package Polynomial.View;

import Polynomial.Polynomial;
import Polynomial.PolynomialOperation;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.awt.image.BufferedImage;

/**
 * Created by billy on 2017-07-25.
 */
public class PolyArithmetic extends VBox {
    private VBox container;
    public PolyArithmetic(){
        container = new VBox(20);
        setLayout();
    }

    private void setLayout() {
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(20));
        setSpacing(20);

        Label title = new Label("Polynomial Arithmetic");
        title.setFont(Font.font(24));

        Button generate = new Button("Generate");
        generate.setOnMouseClicked(this::onGenerateMouseClick);

        getChildren().addAll(title, new Separator(), generate, container);
    }

    private void onGenerateMouseClick(MouseEvent mouseEvent) {

        container.getChildren().clear();

        Polynomial poly1 = Polynomial.rand(9);
        Polynomial poly2 = Polynomial.rand(9);

        TeXFormula tex = new TeXFormula(poly1.toString());
        java.awt.Image awtImage = tex.createBufferedImage(TeXConstants.STYLE_TEXT, 16, java.awt.Color.BLACK, null);
        Image fxImage = SwingFXUtils.toFXImage((BufferedImage) awtImage, null);

        TeXFormula tex2 = new TeXFormula(poly2.toString());
        java.awt.Image awtImage2 = tex2.createBufferedImage(TeXConstants.STYLE_TEXT, 16, java.awt.Color.BLACK, null);
        Image fxImage2 = SwingFXUtils.toFXImage((BufferedImage) awtImage2, null);

        Button plotSumBtn = new Button("Plot the summation!");
        plotSumBtn.setOnAction(event -> {
            PolynomialOperation polynomialOperation = new PolynomialOperation();

            Polynomial poly = polynomialOperation.addition(poly1, poly2);
            SecWindow.Plot(poly);
        });

        Button plotProductBtn = new Button("Plot the multiplication!");
        plotProductBtn.setOnAction(event -> {
            PolynomialOperation polynomialOperation = new PolynomialOperation();

            Polynomial poly = polynomialOperation.multiplication(poly1, poly2);
            SecWindow.Plot(poly);
        });

        container.getChildren().addAll(new ImageView(fxImage), new ImageView(fxImage2), new HBox(10, plotSumBtn, plotProductBtn));

    }
}
