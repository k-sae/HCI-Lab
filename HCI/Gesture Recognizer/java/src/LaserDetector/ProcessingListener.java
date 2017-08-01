package LaserDetector;

import org.opencv.core.Mat;

/**
 * Created by kareem on 7/2/17.
 */
interface ProcessingListener {
    void onFinish(Mat mat);
}
