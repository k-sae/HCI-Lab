package Control;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by PC - MeiR on 7/14/2017.
 */
public class test {
    public static void main(String [] args){

        Thresholder thresholder = new Thresholder();
        BufferedImage img = null;
        try {
            try {
                img = ImageIO.read(new File("F:\\HCI\\brain0.png"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
long [] x = new Histogram(img).getGray();
        for (int i=0;i<256;i++)
        System.out.println(i + "  : " +x[i]);






    }




}
