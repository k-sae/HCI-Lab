package Control;

import ij.plugin.DICOM;

import java.awt.image.BufferedImage;

/**
 * Created by kareem on 7/13/17.
 */
public class Thresholder {
    //TODO #Amr
    //          ONLY and ONLY implement one of them
    //          iam suggesting the first one because it more native

    /**
     * use Otsu thresholding algo to segment image
     * @param input a gray scale image to start segmentation
     * @return new instance of buffered image
     */
    public BufferedImage startThresholding(BufferedImage input)
    {
        return input;
    }
    public DICOM startThresholding(DICOM input)
    {
        return input;
    }
    //TODO #hazem
    //              ask Amr for what u shall do
    public Histogram getHistogram()
    {
        return new Histogram();
    }
}
