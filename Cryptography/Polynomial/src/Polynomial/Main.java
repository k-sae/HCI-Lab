package Polynomial;

import Polynomial.View.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;

import javafx.scene.image.Image;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane graph =new Pane();
        PolynomialView polynomialView =new PolynomialView() {
            @Override
            public void onDraw(LineChart<Number, Number> chart) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        graph.getChildren().add(chart);
                    }
                });
            }
        };
        polynomialView.start();
        Parent root = new MainWindow();
        primaryStage.setTitle("Polynomial Evaluator");
        primaryStage.getIcons().add(new Image("file:img/icon.jpg"));
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
