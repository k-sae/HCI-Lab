import Control.Histogram;
import Control.Thresholder;
import View.HistogramViewer;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.DICOM;

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
public class Main {
    public static void main(String... args)
    {
        //read image from file
        DICOM bufferedImage = Utils.readImageAsDICOM("imgs/P000100.dcm");
        //start the otsu thresholder algo
        Thresholder thresholder= new Thresholder();
        thresholder.startThresholding(Utils.toGrayScale(Utils.dcmToBuffered(bufferedImage)));
        //get the histogram and pass it to the viewer
        Histogram h =new Histogram(Utils.dcmToBuffered(Utils.toGrayScale(bufferedImage)));
        long gray[] =h.getGray();
        //   HistogramViewer histogramViewer = new HistogramViewer(thresholder.getHistogram());
        //add this to the ui
        //start ui up here
    }
}
