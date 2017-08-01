package LaserDetector;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by kareem on 7/2/17.
 */
//TODO #kareem
    // provide a setters for Laser type (shiny red , normal red , green , etc)
public class LaserDetector {
    private ScheduledExecutorService timer;
    private VideoCapture capture;
    private List<OnLaserDetectionListener> onLaserDetectionListeners;
    private List<OnFrameProcessedListener> onFrameProcessedListeners;
    private List<OnMaskProcessedListener> onMaskProcessedListeners;
    private Point laserCenter = new Point();
    private Mat mask = new Mat();
    private Mat hsv = new Mat();
    private Mat heir = new Mat();
    final List<MatOfPoint> matOfPoints = new ArrayList<>();
    Mat frame = new Mat();
    public LaserDetector() {
        onFrameProcessedListeners = new ArrayList<>();
        onMaskProcessedListeners = new ArrayList<>();
        onLaserDetectionListeners = new ArrayList<>();
    }

    public void startDetecting() {
        capture = new VideoCapture();
        capture.open(0);
        Runnable frameGrabber = new Runnable() {

            @Override
            public void run() {
                // effectively grab and process a single frame
                grabFrame();
                // convert and show the frame
                //TODO trigger listener up here
            }
        };

        this.timer = Executors.newSingleThreadScheduledExecutor();
        this.timer.scheduleAtFixedRate(frameGrabber, 0, 20, TimeUnit.MILLISECONDS);
    }

    private void grabFrame() {
        // init everything


        // check if the capture is open
        if (this.capture.isOpened()) {
            try {
                // read the current frame
                this.capture.read(frame);

                // if the frame is not empty, process it
                if (!frame.empty()){
                    trackLaser();
                }

            } catch (Exception e) {
                // log the error
                e.printStackTrace();
            }
        }

    }
    private void trackLaser()
    {

        Imgproc.cvtColor(frame,hsv , Imgproc.COLOR_BGR2HSV);
        //shiny RED Color
        Scalar lowerScale = new Scalar(0, 0, 255);
        Scalar upperScale = new Scalar(255, 255, 255);

        Core.inRange(hsv, lowerScale, upperScale, mask);
        matOfPoints.clear();
        Imgproc.findContours(mask, matOfPoints, heir , Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        if (matOfPoints.size() > 0) {

            float[] radius = new float[1];

            Point[] matOfPoint = matOfPoints.get(maxPoint(matOfPoints)).toArray();
            Imgproc.minEnclosingCircle(new MatOfPoint2f(matOfPoint), laserCenter,radius);
            //Trigger Listener up here
            Imgproc.circle(frame, laserCenter, 7, new Scalar(255, 0, 0), 2);
            Imgproc.circle(mask, laserCenter, 7, new Scalar(255, 0, 0), 2);
            triggerLaserDetectionFrame(laserCenter);

        }
        triggerProcessingListeners(onFrameProcessedListeners, frame);
        triggerProcessingListeners(onMaskProcessedListeners, mask);

    }
    private int maxPoint(List<MatOfPoint> matOfPoints)
    {
        int matOfPoint= 0;
        double maxArea = 0;
        for (int i = 0; i < matOfPoints.size(); i++) {
            double area = Imgproc.contourArea(matOfPoints.get(i).clone());
            if (maxArea < area) {
                maxArea = area;
                matOfPoint = i;
            }
        }
        return matOfPoint;
    }

    //Listeners down here Just ignore them :)

    public void setOnLaserDetectionListeners(OnLaserDetectionListener onLaserDetectionListener)
    {
        onLaserDetectionListeners.add(onLaserDetectionListener);
    }
    public void setOnFrameProcessedListeners(OnFrameProcessedListener onFrameProcessedListener)
    {
        onFrameProcessedListeners.add(onFrameProcessedListener);
    }
    public void removeOnFrameProcessedListeners(OnFrameProcessedListener onFrameProcessedListener)
    {
        onFrameProcessedListeners.remove(onFrameProcessedListener);
    }
    public void setOnMaskProcessedListeners(OnMaskProcessedListener onMaskProcessedListener)
    {
        onMaskProcessedListeners.add(onMaskProcessedListener);
    }

    private void triggerProcessingListeners(List<?> processingListeners, Mat mat)
    {
        List<ProcessingListener> processingListeners1 = (List<ProcessingListener>) processingListeners;
        for (int i = 0; i < (processingListeners1).size(); i++) {
            ProcessingListener processingListener = processingListeners1.get(i);
            processingListener.onFinish(mat);
        }
    }

    private void triggerLaserDetectionFrame(Point point)
    {
        for (OnLaserDetectionListener onLaserDetectionListener: onLaserDetectionListeners
             ) {
            onLaserDetectionListener.onDetection(point);
        }
    }
}
