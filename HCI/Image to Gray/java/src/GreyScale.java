import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by kareem on 6/29/17.
 */
public class GreyScale extends Application {
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
        ImageView imageView2 = new ImageView();
        root.setLeft(imageView1);
        root.setRight(imageView2);
        startVideo(imageView1);
        primaryStage.setTitle("Grey scale");
        primaryStage.setScene(new Scene(root, 900, 400));
        primaryStage.show();
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

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
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
                if (!frame.empty()) {
                    Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
                }

            } catch (Exception e) {
                // log the error
                System.err.println("Exception during the image elaboration: " + e);
            }
        }

        return frame;
    }
    private void updateImageView(ImageView view, Image image)
    {
        Utils.onFXThread(view.imageProperty(), image);
    }

}
