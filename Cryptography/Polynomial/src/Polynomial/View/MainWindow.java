
package Polynomial.View;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private VBox container;
    BufferedImage thresholdingImage = null;
    boolean clickedMask=true;
    public MainWindow(){
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(20));
        setSpacing(20);

        Label title = new Label("Otsu Thresholding");
        title.setFont(Font.font(30));

        container = new VBox(50);
        container.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(container);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        getChildren().addAll(title, new Separator(),scrollPane);
    }
    private void addImg(Image image, HBox histogram)
    {
        Label imgContainer = new Label();
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        imgContainer.setGraphic(imageView);

        BorderPane borderPane = new BorderPane(null,null,histogram,null,imgContainer);
        borderPane.setPadding(new Insets(0,40,0,40));

        container.getChildren().addAll(borderPane);
    }
}