package Polynomial.View;

import Polynomial.Polynomial;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
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
        Button interpolateBtn = new Button("Interpolate a Polynomial");
        Button addBtn = new Button("Add two Polynomial");
        Button multiplyBtn = new Button("Multiply two Polynomial");

        evaluateBtn.setOnAction(event -> changeScene(new SecWindow(new PolyEvaluator())));
        interpolateBtn.setOnAction(event -> changeScene(new SecWindow(new PolyInterpolater())));
        //addBtn.setOnAction(event -> changeScene(new SecWindow()));
        //multiplyBtn.setOnAction(event -> changeScene(new SecWindow()));

        getChildren().addAll(title, new Separator(), chooseLbl, evaluateBtn, interpolateBtn, addBtn, multiplyBtn);

    }
    private void changeScene(Parent node){
        stage.setScene(new Scene(node, 1280, 720));
    }
    public static void Back(){
        stage.setScene(new Scene(new MainWindow(stage), 600, 400));
    }

}