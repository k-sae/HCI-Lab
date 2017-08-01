package View;
import LaserDetector.LaserDetector;
import LaserDetector.OnFrameProcessedListener;
import Model.FileManager;
import ShapesRecognizer.Recognizer;
import ShapesRecognizer.Result;
import TimeOuter.TimeOuter;
import com.google.gson.Gson;
import com.sun.org.apache.regexp.internal.RE;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kareem on 7/1/17.
 */
//TODO use video writer to export resultTA
public class Main extends Application implements OnFrameProcessedListener {
    Text resultTA;
    ImageView frame;
    LaserDetector laserDetector;
    TimeOuter timeOuter;
    Recognizer recognizer = new Recognizer();
    Result result;
    Pane stackPane;

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        launch(args);
    }

    ArrayList<ShapesRecognizer.Point> points = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        frame = new ImageView();
        resultTA = new Text();
        stackPane = new Pane();
        stackPane.getChildren().add(frame);
        root.setLeft(stackPane);
        root.setCenter(resultTA);
        primaryStage.setTitle("Capture Color");
        primaryStage.setScene(new Scene(root, 1280, 400));
        startLaserDetection();
        if (FileManager.getInstance().exists("Templates.json"))
        {
            recognizer = new Gson().fromJson(FileManager.getInstance().read("Templates.json"),Recognizer.class);
        }
        else
        {
            recognizer = new Recognizer();
        }
        primaryStage.show();
    }

    //overriding stop to close all Active threads
    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Rendering, Don't Close NOW !!");
        System.exit(0);
    }

    private void updateImageView(ImageView view, Mat mat) {
        Utils.onFXThread(view.imageProperty(), Utils.mat2Image(mat));
    }

    private void startLaserDetection() {
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
                clearLines();
                points.clear();
                System.out.println(result.Name);
                updateResult();
            }
        };
        timeOuter.setTimeout(200);
        timeOuter.start();
    }

    private void onLaserDetection(Point point) {
        //TODO
        timeOuter.extend();
        points.add(new ShapesRecognizer.Point(point.x, point.y));
        if (points.size() < 2) return;
        ShapesRecognizer.Point lastPoint = points.get(points.size() - 2);
        drawLine(lastPoint.x, lastPoint.y, point.x, point.y);
    }

    @Override
    public void onFinish(Mat frame) {
        //For Debugging
        updateImageView(this.frame, frame);

    }

    public void onFinishMask(Mat frame) {
        //For D
    }

    private void drawLine(double x1, double y1, double x2, double y2) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Line line = new Line(x1, y1, x2, y2);
                line.setStroke(Color.RED);
                stackPane.getChildren().add(line);
            }
        });

    }

    private void clearLines() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stackPane.getChildren().clear();
                stackPane.getChildren().add(frame);
            }
        });

    }

    private void updateResult() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                NumberFormat formatter = new DecimalFormat("#0.00");
                resultTA.setText(result.Name + " (" +  formatter.format(result.Score) + ") ");
            }
        });
    }
}
