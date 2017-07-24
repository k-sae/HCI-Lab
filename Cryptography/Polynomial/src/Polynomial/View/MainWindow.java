
package Polynomial.View;

import Polynomial.Polynomial;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by billy on 2017-07-14.
 */
public class MainWindow extends VBox {
    private PolyViewer polyViewer = new PolyViewer();
    public MainWindow(){
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(20));
        setSpacing(20);

        Label title = new Label("Polynomial Evaluator");
        title.setFont(Font.font(30));

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
        polyViewer.setPolynomial(polynomial);
    }
}