import Control.Histogram;
import Control.Thresholder;
import View.HistogramViewer;
import ij.ImagePlus;
import ij.plugin.DICOM;
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
        //read image from file
        launch(args);
        //add this to the ui
        //start ui up here
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DICOM bufferedImage = Utils.readImageAsDICOM("imgs/P000100.dcm");
        String url = "imgs/rgb.dcm";
        ImagePlus imagePlus;
        imagePlus = new ImagePlus(url);
        //start the otsu thresholder algo
        System.out.println(Utils.imagePlusToBuffered(imagePlus).getColorModel().getPixelSize());
        Thresholder thresholder= new Thresholder();
        thresholder.startThresholding(Utils.imagePlusToBuffered(Utils.toGrayScale(imagePlus)));
        //get the histogram and pass it to the viewer
        System.out.println(Utils.imagePlusToBuffered(imagePlus).getColorModel().getPixelSize());
        Histogram h =new Histogram(Utils.imagePlusToBuffered(Utils.toGrayScale(imagePlus)));
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
