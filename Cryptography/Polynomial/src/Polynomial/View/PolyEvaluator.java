package Polynomial.View;

import Polynomial.Polynomial;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.math.BigInteger;

/**
 * Created by billy on 2017-07-25.
 */
public class PolyEvaluator extends VBox{
    private VBox container;
    public PolyEvaluator(){
        container = new VBox(20);
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(10));
        setSpacing(20);

        Label title = new Label("Polynomial Evaluator");
        title.setFont(Font.font(24));

        ScrollPane scrollPane = new ScrollPane(setContainer());
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        getChildren().addAll(title, new Separator(),scrollPane, container);
    }
    private VBox setContainer()
    {
        VBox container = new VBox(20);
        container.setAlignment(Pos.TOP_CENTER);
        Button generate = new Button("Generate");
        generate.setOnMouseClicked(this::onGenerateMouseClick);
        container.getChildren().addAll(generate);
        return container;
    }

    private void onGenerateMouseClick(MouseEvent mouseEvent) {
        Polynomial polynomial = Polynomial.rand(9);
        SecWindow.Plot(polynomial);

        container.getChildren().clear();
        Label label = new Label("Enter the value of the x:");
        label.setFont(Font.font(18));

        NumericTextField numericTextField = new NumericTextField();
        numericTextField.setPromptText("X value");
        numericTextField.setMaxWidth(60);

        Label resultLbl = new Label();
        resultLbl.setFont(Font.font(18));

        container.setAlignment(Pos.TOP_CENTER);
        container.getChildren().addAll(label, numericTextField, resultLbl);

        numericTextField.setOnKeyReleased(event -> {
            if(!numericTextField.getText().equals("") && !numericTextField.getText().equals("-") && !numericTextField.getText().contains(".")) {
                BigInteger result = polynomial.evaluate(new BigInteger(numericTextField.getText()));
                resultLbl.setText("The result is: " + result);

            }
        });

    }
}
