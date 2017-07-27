package Polynomial.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by billy on 2017-07-14.
 */
public class MainWindow extends VBox {
    private static Stage stage;
    public MainWindow(Stage stage){
        this.stage = stage;
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(20));
        setSpacing(20);

        Label title = new Label("Polynomial");
        title.setFont(Font.font(30));

        Label chooseLbl = new Label("Choose an option");
        chooseLbl.setFont(Font.font(20));

        Button evaluateBtn = new Button("Evaluate a Polynomial");
        Button interpolateBtn = new Button("Interpolate a Polynomials");
        Button arithmeticBtn = new Button("Add/Multiply two Polynomials");
        Button secretArithmeticBtn = new Button("Secret Add/Multiply two Polynomials");

        evaluateBtn.setOnAction(event -> changeScene(new SecWindow(new PolyEvaluator())));
        interpolateBtn.setOnAction(event -> changeScene(new SecWindow(new PolyInterpolater())));
        arithmeticBtn.setOnAction(event -> changeScene(new SecWindow(new PolyArithmetic())));
        secretArithmeticBtn.setOnAction(event -> changeScene(new SecWindow(new PolSecretArithmetic())));

        getChildren().addAll(title, new Separator(), chooseLbl, evaluateBtn, interpolateBtn, arithmeticBtn, secretArithmeticBtn);

    }
    private void changeScene(Parent node){
        stage.setScene(new Scene(node, 1280, 720));
    }
    public static void Back(){
        stage.setScene(new Scene(new MainWindow(stage), 600, 400));
    }

}