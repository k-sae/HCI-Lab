package LaserDetector;

import org.opencv.core.Mat;

/**
 * Created by kareem on 7/2/17.
 */
public interface OnMaskProcessedListener extends ProcessingListener {
    @Override
    void onFinish(Mat mask);
}
