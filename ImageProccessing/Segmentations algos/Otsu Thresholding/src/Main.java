import Control.Histogram;
import Control.Thresholder;
import ij.ImagePlus;

import java.awt.image.BufferedImage;

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
        String url = "imgs/P000100.dcm";
//        DICOM bufferedImage = Utils.readImageAsDICOM("imgs/tt.png");
        ImagePlus imagePlus;
        if (url.endsWith(".dcm")) {
            imagePlus =  Utils.readImageAsDICOM(url);
        }
        else imagePlus = new ImagePlus(url);
        //start the otsu thresholder algo
        Thresholder thresholder= new Thresholder();
        thresholder.startThresholding(Utils.toGrayScale(Utils.imagePlusToBuffered(imagePlus)));
        //get the histogram and pass it to the viewer
        Histogram h =new Histogram(Utils.imagePlusToBuffered(Utils.toGrayScale(imagePlus)));
        long gray[] =h.getGray();
        //   HistogramViewer histogramViewer = new HistogramViewer(thresholder.getHistogram());
        //add this to the ui
        //start ui up here
    }
}
