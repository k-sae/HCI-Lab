package View;

import Control.Histogram;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import Control.Histogram;

/**
 * Created by kareem on 7/14/17.
 */

//TODO #khaled
    // wait for hazem to finish histogram first before implementing this
    //u may need to extend a ui element u r free to extend any one
    // hf :)
public class HistogramViewer extends HBox {
    HBox Hb=new HBox();
    public HBox HistogramViewer(Histogram histogram)
    {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String, Number> chartHistogram
                = new LineChart<>(xAxis, yAxis);
        chartHistogram.setCreateSymbols(false);
        if(histogram.getsucess()){
            chartHistogram.getData().add(histogram.getSeriesGray());
        }
        Hb.getChildren().add(chartHistogram);
        return Hb;
    }



}
