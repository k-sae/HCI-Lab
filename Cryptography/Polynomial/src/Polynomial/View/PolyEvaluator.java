package Polynomial.View;

import Polynomial.Polynomial;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Created by billy on 2017-07-25.
 */
public class PolyEvaluator extends VBox{
    private static PolyViewer polyViewer = new PolyViewer();
    public PolyEvaluator(){
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(20));
        setSpacing(20);

        Label title = new Label("Polynomial Evaluator");
        title.setFont(Font.font(24));

        setContainer();

        ScrollPane scrollPane = new ScrollPane(setContainer());
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        getChildren().addAll(title, new Separator(),scrollPane);
    }
    private VBox setContainer()
    {
        VBox container = new VBox(20);
        container.setAlignment(Pos.TOP_CENTER);
        Button generate    = new Button("Generate");
        generate.setOnMouseClicked(this::onGenerateMouseClick);
        container.getChildren().addAll(generate, polyViewer);
        return container;
    }

    private void onGenerateMouseClick(MouseEvent mouseEvent) {
        Polynomial polynomial  = Polynomial.rand();
        p(polynomial);
    }
    public static void p(Polynomial pol){
        polyViewer.setPolynomial(pol);
    }
}
