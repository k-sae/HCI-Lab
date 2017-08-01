package LaserDetector;

import org.opencv.core.Point;

/**
 * Created by kareem on 7/2/17.
 */
public interface OnLaserDetectionListener {
    void onDetection(Point point);
}
