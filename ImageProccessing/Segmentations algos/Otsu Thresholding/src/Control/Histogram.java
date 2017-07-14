package Control;

import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.*;
import java.awt.image.*;

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
        }
        Raster raster = image.getData();
        DataBuffer buffer = raster.getDataBuffer();
        DataBufferByte byteBuffer = (DataBufferByte) buffer;
        byte[] srcData = byteBuffer.getData(0);
        for (int y = 0; y < srcData.length; y++) {
            int g= 0xFF & srcData[y];

                gray[g]++;

            }


        seriesGray = new XYChart.Series();
        seriesGray.setName("gray");
        for (int i = 0; i < 256; i++) {
            seriesGray.getData().add(new XYChart.Data(String.valueOf(i), gray[i]));
        }
        success = true;
    }

    public long[] getGray() {
        return gray;
    }

    public XYChart.Series getSeriesGray() {
        return seriesGray;
    }

    public boolean getsucess(){return success;}
}