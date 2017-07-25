package Polynomial.View;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by billy on 2017-07-25.
 */
public class SecWindow extends BorderPane {
    private static PolyViewer polyViewer = new PolyViewer();
    private Parent node;
    public SecWindow(Parent node){
        this.node = node;
        setLayout();
    }

    private void setLayout(){

        setPadding(new Insets(20));

        Label title = new Label("Polynomial");
        title.setFont(Font.font(30));

        ScrollPane PEscrollPane = new ScrollPane(polyViewer);
        PEscrollPane.setFitToWidth(true);
        PEscrollPane.setStyle("-fx-background-color:transparent;");

        ScrollPane PIscrollPane = new ScrollPane(node);
        PIscrollPane.setFitToWidth(true);
        PIscrollPane.setStyle("-fx-background-color:transparent;");

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(title, new Separator());
        PIscrollPane.setPrefWidth(400);

        Button backBtn = new Button("<-Back");
        backBtn.setOnAction(event -> MainWindow.Back());

        setTop(vBox);
        setLeft(PIscrollPane);
        setRight(PEscrollPane);
        setBottom(backBtn);

        setAlignment(this.getTop(),Pos.TOP_CENTER);
    }

    public static void Plot(Polynomial pol){
        polyViewer.setPolynomial(pol);
    }

}
