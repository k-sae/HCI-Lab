package Polynomial.View;

import Polynomial.Polynomial;
import Polynomial.PolynomialOperation;
import Polynomial.Secret;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import java.math.BigInteger;

/**
 * Created by billy on 2017-07-27.
 */
public class PolSecretArithmetic extends VBox{
    public PolSecretArithmetic(){
        setLayout();
    }

    private void setLayout() {
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(20));
        setSpacing(20);

        Label title = new Label("Polynomial Secret Arithmetic");
        title.setFont(Font.font(24));

        VBox container = new VBox(10);
        VBox container2 = new VBox(10);

        Button plotSumBtn = new Button("Summation!");
        plotSumBtn.setOnAction(event -> {
            container.getChildren().clear();
            container2.getChildren().clear();

            Label s1Lbl = new Label("Enter Secret 1:");
            Label s2Lbl = new Label("Enter Secret 2:");
            NumericTextField s1 = new NumericTextField();
            NumericTextField s2 = new NumericTextField();

            Button enterBtn = new Button("Enter");
            enterBtn.setOnAction(event1 -> {
                container2.getChildren().clear();
                Polynomial p[]= Secret.polynimalGenerate(Integer.parseInt(s1.getText()),Integer.parseInt(s2.getText()));
                BigInteger[] points= Secret.EvaluatePoints(p);

                TeXFormula tex = new TeXFormula(p[0].toString());
                java.awt.Image awtImage = tex.createBufferedImage(TeXConstants.STYLE_TEXT, 16, java.awt.Color.BLACK, null);
                Image fxImage = SwingFXUtils.toFXImage((BufferedImage) awtImage, null);

                TeXFormula tex2 = new TeXFormula(p[1].toString());
                java.awt.Image awtImage2 = tex2.createBufferedImage(TeXConstants.STYLE_TEXT, 16, java.awt.Color.BLACK, null);
                Image fxImage2 = SwingFXUtils.toFXImage((BufferedImage) awtImage2, null);

                PolynomialOperation polynomialOperation = new PolynomialOperation();

                Polynomial poly = polynomialOperation.addition(p[0], p[1]);
                SecWindow.Plot(poly);

                container2.getChildren().addAll(new ImageView(fxImage), new ImageView(fxImage2));
            });

            container.getChildren().addAll(new HBox(10, s1Lbl, s1), new HBox(10, s2Lbl, s2), enterBtn);

        });

        Button plotProductBtn = new Button("Multiplication!");
        plotProductBtn.setOnAction(event -> {
            container.getChildren().clear();
            container2.getChildren().clear();

            NumericTextField s1 = new NumericTextField();
            NumericTextField s2 = new NumericTextField();
            NumericTextField s3 = new NumericTextField();

            Button generate = new Button("Generate");
            generate.setOnAction(event1 -> {
                Polynomial p[]= Secret.polynimalGenerate(Integer.parseInt(s1.getText()),Integer.parseInt(s2.getText()));
                BigInteger[] points= Secret.EvaluatePoints(p);
                PolynomialOperation polynomialOperation = new PolynomialOperation();

                Polynomial poly = polynomialOperation.addition(p[0], p[1]);
                SecWindow.Plot(poly);
            });

            container.getChildren().addAll(s1, s2, s3, generate);

        });

        getChildren().addAll(title, new Separator(), new HBox(10, plotSumBtn, plotProductBtn), container, container2);
    }

}
