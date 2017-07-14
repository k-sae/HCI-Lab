import ij.ImagePlus;
import ij.plugin.DICOM;
import ij.process.ImageConverter;

import java.awt.image.BufferedImage;

/**
 * Created by kareem on 7/13/17.
 * Don't try to figure it out yub i included the whole imagej project up here :)
 */
public class Utils {
    public static BufferedImage imagePlusToBuffered(ImagePlus src)
    {
        return src.getBufferedImage();
    }
    public static BufferedImage readImage(String url)
    {
        return readImageAsDICOM(url).getBufferedImage();
    }
    public static DICOM readImageAsDICOM(String url)
    {
        DICOM dicom = new DICOM();
        dicom.open(url);
        return dicom;
    }

    //TODO #belal
    //          ONLY and ONLY implement one of them
    //          DON'T and DON'T wast time on both of them
    //          again one function is enough :)
    //          iam using the first one im main function by default
    //          tell me if u decided to use the second
    //          hf
    //          don't del this comments it will be good memories
    //          another thing ...
    //          nvm i forgot :D

    public static ImagePlus toGrayScale(ImagePlus imagePlus)
    {
        new ImageConverter(imagePlus).convertToGray8();

        return imagePlus;
    }
    public static BufferedImage toGrayScale(BufferedImage bufferedImage){
        return bufferedImage;
    }
}
