import Control.Histogram;
import Control.Thresholder;
import View.HistogramViewer;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.DICOM;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by kemo on 13/07/2017.
 */
//TODO #khaled
    //          The whole ui :)
    //          u r free to use swing of fx
public class Main extends Application {
    public static void main(String... args)
    {
        //read image from file
        launch(args);
        //add this to the ui
        //start ui up here
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DICOM bufferedImage = Utils.readImageAsDICOM("imgs/P000100.dcm");
        //start the otsu thresholder algo
        Thresholder thresholder= new Thresholder();
        thresholder.startThresholding(Utils.toGrayScale(Utils.dcmToBuffered(bufferedImage)));
        //get the histogram and pass it to the viewer
        Histogram h =new Histogram(Utils.toGrayScale(Utils.dcmToBuffered(bufferedImage)));
        long gray[] =h.getGray();

        HistogramViewer histogramViewer = new HistogramViewer();
        HBox hb=histogramViewer.HistogramViewer(h);
        StackPane root = new StackPane();
        root.getChildren().add(hb);

        Scene scene = new Scene(root, 1000, 500);

        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
