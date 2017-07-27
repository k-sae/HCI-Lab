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

        Button plotSumBtn = new Button("Addition");
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

                TeXFormula tex = new TeXFormula("P1(X) = " + p[0].toString());
                java.awt.Image awtImage = tex.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage = SwingFXUtils.toFXImage((BufferedImage) awtImage, null);

                TeXFormula tex2 = new TeXFormula("P2(X) = " + p[1].toString());
                java.awt.Image awtImage2 = tex2.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage2 = SwingFXUtils.toFXImage((BufferedImage) awtImage2, null);

                TeXFormula tex3 = new TeXFormula("a = (s1,P1(s1)+P2(s1))");
                java.awt.Image awtImage3 = tex3.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage3 = SwingFXUtils.toFXImage((BufferedImage) awtImage3, null);

                TeXFormula tex4 = new TeXFormula("a = (" + points[8] + "," + points[9] + ")");
                java.awt.Image awtImage4 = tex4.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage4 = SwingFXUtils.toFXImage((BufferedImage) awtImage4, null);

                TeXFormula tex5 = new TeXFormula("b = (s2,P1(s2)+P2(s2))");
                java.awt.Image awtImage5 = tex5.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage5 = SwingFXUtils.toFXImage((BufferedImage) awtImage5, null);

                TeXFormula tex6 = new TeXFormula("b = (" + points[10] + "," + points[11] + ")");
                java.awt.Image awtImage6 = tex6.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage6 = SwingFXUtils.toFXImage((BufferedImage) awtImage6, null);

                TeXFormula tex7 = new TeXFormula("P3(X) = P1(X) + P2(X)");
                java.awt.Image awtImage7 = tex7.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage7 = SwingFXUtils.toFXImage((BufferedImage) awtImage7, null);

                PolynomialOperation polynomialOperation = new PolynomialOperation();
                Polynomial poly = polynomialOperation.addition(p[0], p[1]);
                SecWindow.Plot(poly);

                container2.getChildren().addAll(new HBox(5, new ImageView(fxImage), new Label("such that P1(0) = " + p[0].evaluate(new BigInteger("0")) + " (The first secret)")), new HBox(5, new ImageView(fxImage2), new Label("such that P2(0) = " + p[1].evaluate(new BigInteger("0")) + " (The second secret)")), new ImageView(fxImage3), new ImageView(fxImage4), new ImageView(fxImage5), new ImageView(fxImage6), new ImageView(fxImage7));
            });

            container.getChildren().addAll(new HBox(10, s1Lbl, s1), new HBox(10, s2Lbl, s2), enterBtn);

        });

        Button plotProductBtn = new Button("Multiplication");
        plotProductBtn.setOnAction(event -> {
            container.getChildren().clear();
            container2.getChildren().clear();

            Label s1Lbl = new Label("Enter Secret 1:");
            Label s2Lbl = new Label("Enter Secret 2:");
            Label s3Lbl = new Label("Enter Secret 3:");

            NumericTextField s1 = new NumericTextField();
            NumericTextField s2 = new NumericTextField();
            NumericTextField s3 = new NumericTextField();

            Button enterBtn = new Button("Enter");
            enterBtn.setOnAction(event1 -> {
                container2.getChildren().clear();
                Polynomial p[]= Secret.polynimalGenerate(Integer.parseInt(s1.getText()),Integer.parseInt(s2.getText()));
                BigInteger[] points= Secret.EvaluateMultiplactionPoints(p,BigInteger.valueOf(Integer.parseInt(s3.getText())));

                TeXFormula tex = new TeXFormula("P1(X) = " + p[0].toString());
                java.awt.Image awtImage = tex.createBufferedImage(TeXConstants.STYLE_TEXT, 12, java.awt.Color.BLACK, null);
                Image fxImage = SwingFXUtils.toFXImage((BufferedImage) awtImage, null);

                TeXFormula tex2 = new TeXFormula("P2(X) = " + p[1].toString());
                java.awt.Image awtImage2 = tex2.createBufferedImage(TeXConstants.STYLE_TEXT, 12, java.awt.Color.BLACK, null);
                Image fxImage2 = SwingFXUtils.toFXImage((BufferedImage) awtImage2, null);

                TeXFormula tex3 = new TeXFormula("a = (s1,P1(s1)*P2(s1))");
                java.awt.Image awtImage3 = tex3.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage3 = SwingFXUtils.toFXImage((BufferedImage) awtImage3, null);

                TeXFormula tex4 = new TeXFormula("a = (" + points[12] + "," + points[13] + ")");
                java.awt.Image awtImage4 = tex4.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage4 = SwingFXUtils.toFXImage((BufferedImage) awtImage4, null);

                TeXFormula tex5 = new TeXFormula("b = (s2,P1(s2)*P2(s2))");
                java.awt.Image awtImage5 = tex5.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage5 = SwingFXUtils.toFXImage((BufferedImage) awtImage5, null);

                TeXFormula tex6 = new TeXFormula("b = (" + points[14] + "," + points[15] + ")");
                java.awt.Image awtImage6 = tex6.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage6 = SwingFXUtils.toFXImage((BufferedImage) awtImage6, null);

                TeXFormula tex7 = new TeXFormula("c = (s3,P1(s3)*P2(s3))");
                java.awt.Image awtImage7 = tex7.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage7 = SwingFXUtils.toFXImage((BufferedImage) awtImage7, null);

                TeXFormula tex8 = new TeXFormula("c = (" + points[16] + "," + points[17] + ")");
                java.awt.Image awtImage8 = tex8.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage8 = SwingFXUtils.toFXImage((BufferedImage) awtImage8, null);

                TeXFormula tex9 = new TeXFormula("P3(X) = P1(X) * P2(X)");
                java.awt.Image awtImage9 = tex9.createBufferedImage(TeXConstants.STYLE_TEXT, 14, java.awt.Color.BLACK, null);
                Image fxImage9 = SwingFXUtils.toFXImage((BufferedImage) awtImage9, null);

                PolynomialOperation polynomialOperation = new PolynomialOperation();
                Polynomial poly = polynomialOperation.multiplication(p[0], p[1]);
                SecWindow.Plot(poly);

                container2.getChildren().addAll(new HBox(5, new ImageView(fxImage), new Label("such that P1(0) = " + p[0].evaluate(new BigInteger("0")) + " (The first secret)")), new HBox(5, new ImageView(fxImage2), new Label("such that P2(0) = " + p[1].evaluate(new BigInteger("0")) + " (The second secret)")), new ImageView(fxImage3), new ImageView(fxImage4), new ImageView(fxImage5), new ImageView(fxImage6), new ImageView(fxImage7), new ImageView(fxImage8), new ImageView(fxImage9));
            });

            container.getChildren().addAll(new HBox(10, s1Lbl, s1), new HBox(10, s2Lbl, s2), new HBox(10, s3Lbl, s3), enterBtn);

        });

        getChildren().addAll(title, new Separator(), new HBox(10, plotSumBtn, plotProductBtn), container, container2);
    }

}
