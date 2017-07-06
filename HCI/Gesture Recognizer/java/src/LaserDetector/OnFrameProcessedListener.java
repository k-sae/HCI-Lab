package LaserDetector;

import org.opencv.core.Mat;

/**
 * Created by kareem on 7/2/17.
 */
public interface OnFrameProcessedListener extends ProcessingListener {
    @Override
    void onFinish(Mat frame);
}
