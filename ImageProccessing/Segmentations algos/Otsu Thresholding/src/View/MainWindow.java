package View;

import com.sun.javafx.scene.control.skin.Utils;
import Control.Histogram;
import Control.Thresholder;
import ij.ImagePlus;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import Utils;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;


/**
 * Created by billy on 2017-07-14.
 */
public class MainWindow extends VBox {
    private VBox container;
    public MainWindow(){
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(20));
        setSpacing(20);

        Label title = new Label("Otsu Thresholding");
        title.setFont(Font.font(30));

        Label uploadImgLbl = new Label("Choose an image:");
        uploadImgLbl.setFont(Font.font(20));

        container = new VBox(50);
        container.setAlignment(Pos.TOP_CENTER);

        Button uploadImgBtn = new Button("Open image");
        uploadImgBtn.setOnAction(event -> {

            ImagePlus imgp = imageFilter();

            if(imgp != null) {
                container.getChildren().clear();
                //TODO Pass to addImg function the histogram -in HBox-
/*
                ImagePlus imagePlus =imgp;
                Thresholder thresholder = new Thresholder();
                thresholder.startThresholding(Utils.imagePlusToBuffered(Utils.toGrayScale(imagePlus)));

                Histogram histogram = new Histogram();
                BufferedImage thresholderingImage =  thresholder.startThresholding(Utils.imagePlusToBuffered(Utils.toGrayScale(imagePlus)));
                histogram.GenerateHistogram(Utils.imagePlusToBuffered(Utils.toGrayScale(imagePlus)));
                long originalHistogram[] = histogram.getGrayHistogram();
                histogram.GenerateHistogram(thresholderingImage);
                long thresholderingImageHistogram[] = histogram.getGrayHistogram();

                HistogramViewer histogramViewer = new HistogramViewer();
                HBox hb=histogramViewer.HistogramViewer(histogram);
*/
                addImg(imgp, new HBox());

                Button applyBtn = new Button("Apply Mask");
                applyBtn.setOnAction(event1 -> {

                    //TODO Pass to addImg function the Image -in ImagePlus- after the Algorithm is done and the histogram -in HBox-
                    addImg(imgp, new HBox());
                });

                container.getChildren().add(applyBtn);

            }else
                System.out.println("null");
        });


        HBox uploadImg = new HBox(20);
        uploadImg.setAlignment(Pos.CENTER);
        uploadImg.getChildren().addAll(uploadImgLbl, uploadImgBtn);

        ScrollPane scrollPane = new ScrollPane(container);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        getChildren().addAll(title, new Separator(), uploadImg, scrollPane);
    }
    private ImagePlus imageFilter(){

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterTiff = new FileChooser.ExtensionFilter("TIF files (*.tif)", "*.TIF");
        FileChooser.ExtensionFilter extFilterDcm = new FileChooser.ExtensionFilter("DCM files (*.dcm)", "*.DCM");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPg,extFilterPNG,extFilterDcm,extFilterTiff);
        File file =fileChooser.showOpenDialog(null);

        if(file != null) {
            ImagePlus imagePlus = new ImagePlus(file.getPath());

            return imagePlus;
        }else
            return null;
    }
    private void addImg(ImagePlus imgp, HBox histogram){

        Image img = SwingFXUtils.toFXImage(imgp.getBufferedImage(), null);

        Label imgContainer = new Label();
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        imgContainer.setGraphic(imageView);

        BorderPane borderPane = new BorderPane(null,null,histogram,null,imgContainer);
        borderPane.setPadding(new Insets(0,40,0,40));

        container.getChildren().addAll(borderPane);

    }
}
