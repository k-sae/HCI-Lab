package View;
import LaserDetector.LaserDetector;
import LaserDetector.OnFrameProcessedListener;
import ShapesRecognizer.Recognizer;
import ShapesRecognizer.Result;
import TimeOuter.TimeOuter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kareem on 7/1/17.
 */
//TODO use video writer to export mask
public class Main extends Application implements OnFrameProcessedListener {
    ImageView mask;
    ImageView frame;
    LaserDetector laserDetector;
    TimeOuter timeOuter;
    Recognizer recognizer = new Recognizer();
    Result result;
    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        launch(args);
    }
    ArrayList<ShapesRecognizer.Point> points = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        frame = new ImageView();
        mask = new ImageView();
        root.setLeft(frame);
        root.setCenter(mask);
        primaryStage.setTitle("Capture Color");
        primaryStage.setScene(new Scene(root, 900, 400));
        startLaserDetection();
        primaryStage.show();
    }
    //overriding stop to close all Active threads
    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Rendering, Don't Close NOW !!");
        System.exit(0);
    }

    private void updateImageView(ImageView view, Mat mat)
    {
        Utils.onFXThread(view.imageProperty(), Utils.mat2Image(mat));
    }
    private void startLaserDetection()
    {
        laserDetector = new LaserDetector();
        //setting listeners
        laserDetector.setOnFrameProcessedListeners(this);
        laserDetector.setOnMaskProcessedListeners(this::onFinishMask);
        laserDetector.setOnLaserDetectionListeners(this::onLaserDetection);
        laserDetector.startDetecting();
        timeOuter = new TimeOuter() {
            @Override
            public void onFinish() {
                if (points.size() < 5) return;
             result = recognizer.Recognize(Arrays.copyOf(points.toArray(), points.size(), ShapesRecognizer.Point[].class));
              points.clear();
                System.out.println(result.Name);
            }
        };
        timeOuter.setTimeout(1000);
        timeOuter.start();
    }

    private void onLaserDetection(Point point) {
        //TODO
        timeOuter.extend();
        points.add(new ShapesRecognizer.Point(point.x, point.y));
    }

    @Override
    public void onFinish(Mat frame) {
        //For Debugging
        updateImageView(this.frame, frame);
    }

    public void onFinishMask(Mat frame) {
        //For D
    }

}
