import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by kareem on 7/1/17.
 */
public class CaptureLaser extends Application {
    ImageView mask;
    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("mat = \n" + mat.dump());
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        ImageView imageView1 = new ImageView();
        mask = new ImageView();
        root.setLeft(imageView1);
        root.setRight(mask);
        startVideo(imageView1);
        primaryStage.setTitle("Capture Color");
        primaryStage.setScene(new Scene(root, 900, 400));
        primaryStage.show();
    }
    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    private VideoCapture capture;
    private ScheduledExecutorService timer;
    private void startVideo(ImageView imageView) {
        capture = new VideoCapture();
        capture.open(0);
        Runnable frameGrabber = new Runnable() {

            @Override
            public void run() {
                // effectively grab and process a single frame
                Mat frame = grabFrame();
                // convert and show the frame
                Image imageToShow = Utils.mat2Image(frame);
                updateImageView(imageView, imageToShow);
            }
        };

        this.timer = Executors.newSingleThreadScheduledExecutor();
        this.timer.scheduleAtFixedRate(frameGrabber, 0, 20, TimeUnit.MILLISECONDS);
    }
    private Mat grabFrame() {
        // init everything
        Mat frame = new Mat();

        // check if the capture is open
        if (this.capture.isOpened()) {
            try {
                // read the current frame
                this.capture.read(frame);

                // if the frame is not empty, process it
                if (!frame.empty()){
                    trackLaser(frame).assignTo(frame);
                  }

            } catch (Exception e) {
                // log the error
                System.err.println("Exception during the image elaboration: " + e);
            }
        }

        return frame;
    }
    private Mat trackLaser(Mat frame)
    {
        Mat mask = new Mat();
        Mat hsv = new Mat();
        Imgproc.cvtColor(frame,hsv , Imgproc.COLOR_BGR2HSV);
        //RED Color
        Scalar lowerScale = new Scalar(0, 0, 255);
        Scalar upperScale = new Scalar(255, 255, 255);
        Core.inRange(hsv, lowerScale, upperScale, mask);
        final List<MatOfPoint> matOfPoints = new ArrayList<>();
        final Mat hierarchy = new Mat();
        Imgproc.findContours(mask, matOfPoints, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        if (matOfPoints.size() > 0) {
            Point center = new Point();
            float[] radius = new float[1];
            Imgproc.minEnclosingCircle(new MatOfPoint2f(matOfPoints.get(maxPoint(matOfPoints)).toArray()),center,radius);
            Imgproc.circle(frame,center , 5, new Scalar(255, 0, 0), 2);
        }
        Image maskImage = Utils.mat2Image(mask);
        updateImageView(this.mask, maskImage);
        return frame ;
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
    private void updateImageView(ImageView view, Image image)
    {
        Utils.onFXThread(view.imageProperty(), image);
    }

}
