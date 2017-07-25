
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
public class MainWindow extends BorderPane {

    public MainWindow(){
        setLayout();
    }

    private void setLayout(){

        setPadding(new Insets(20));

        Label title = new Label("Polynomial");
        title.setFont(Font.font(30));


        ScrollPane PEscrollPane = new ScrollPane(new PolyEvaluator());
        PEscrollPane.setFitToWidth(true);
        PEscrollPane.setStyle("-fx-background-color:transparent;");

        ScrollPane PIscrollPane = new ScrollPane(new PolyInterpolater());
        PIscrollPane.setFitToWidth(true);
        PIscrollPane.setStyle("-fx-background-color:transparent;");

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(title, new Separator());
        PIscrollPane.setPrefWidth(500);
        setTop(vBox);
        setRight(PIscrollPane);
        setLeft(PEscrollPane);


        setAlignment(this.getTop(),Pos.TOP_CENTER);
    }

}