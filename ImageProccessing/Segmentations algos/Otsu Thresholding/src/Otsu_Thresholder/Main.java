package Otsu_Thresholder;

import Otsu_Thresholder.Control.Histogram;
import Otsu_Thresholder.Control.Thresholder;
import Otsu_Thresholder.View.HistogramViewer;
import Otsu_Thresholder.View.MainWindow;
import ij.ImagePlus;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;

/**
 * Created by kemo on 13/07/2017.
 */
//TODO #khaled
    //          The whole ui :)
    //          u r free to use swing of fx
public class Main extends Application {
    public static void main(String... args)
    {
        launch(args);
        //add this to the ui
        //start ui up here
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //read image from file
        StackPane root = new StackPane();
        root.getChildren().add(new MainWindow());

        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Otsu Thresholding");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
