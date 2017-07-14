import Control.Histogram;
import Control.Thresholder;
import View.HistogramViewer;
import View.MainWindow;
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
        String url = "imgs/rgb.dcm";
        ImagePlus imagePlus;
        imagePlus = new ImagePlus(url);
        //start the otsu thresholder algo
        System.out.println(Utils.imagePlusToBuffered(imagePlus).getColorModel().getPixelSize());
        Thresholder thresholder= new Thresholder();
        thresholder.startThresholding(Utils.imagePlusToBuffered(Utils.toGrayScale(imagePlus)));
        //get the histogram and pass it to the viewer
        System.out.println(Utils.imagePlusToBuffered(imagePlus).getColorModel().getPixelSize());
        Histogram histogram =new Histogram();
        // The Results Data
        BufferedImage thresholderingImage =  thresholder.startThresholding(Utils.imagePlusToBuffered(Utils.toGrayScale(imagePlus)));
        histogram.GenerateHistogram(Utils.imagePlusToBuffered(Utils.toGrayScale(imagePlus)));
        long originalHistogram[] = histogram.getGrayHistogram();
        histogram.GenerateHistogram(thresholderingImage);
        long thresholderingImageHistogram[] = histogram.getGrayHistogram();
        //Use this to show the histogram
        HistogramViewer histogramViewer = new HistogramViewer();
        HBox hb=histogramViewer.HistogramViewer(histogram);

        StackPane root = new StackPane();
        root.getChildren().add(new MainWindow());

        Scene scene = new Scene(root, 1000, 500);

        primaryStage.setTitle("Otsu Thresholding");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
