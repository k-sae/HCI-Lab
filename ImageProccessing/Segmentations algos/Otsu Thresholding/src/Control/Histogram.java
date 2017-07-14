package Control;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Created by kareem on 7/14/17.
 * this class holds the data needed to view histogram
 */
public class Histogram {
    Raster wr;
    public Histogram(BufferedImage image){
     wr  = image.getRaster() ;
    }
    public Raster getRaster(){
        return this.wr;
    }
}
