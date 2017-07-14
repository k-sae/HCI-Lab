package Control;

import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Created by kareem on 7/14/17.
 * this class holds the data needed to view histogram
 */
public class Histogram {
    private long gray[] = new long[256];
    XYChart.Series seriesGray;
    private boolean success;
    public Histogram(BufferedImage image){
        success = false;
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y <image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }
        for (int i = 0; i < 256; i++) {
            gray[i] =  0;
        }
        PixelReader pixelReader = wr.getPixelReader();
        if (pixelReader == null) {
            return;
        }for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int argb = pixelReader.getArgb(x, y);
                int g = (0xff & (argb >> 8));
                gray[g]++;

            }
        }

        seriesGray = new XYChart.Series();
        seriesGray.setName("gray");
        for (int i = 0; i < 256; i++) {
            seriesGray.getData().add(new XYChart.Data(String.valueOf(i), gray[i]));
        }
        success = true;
    }
    public XYChart.Series getSeriesGray() {
        return seriesGray;
    }
    }