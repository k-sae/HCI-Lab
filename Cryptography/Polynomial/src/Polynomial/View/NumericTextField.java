package Polynomial.View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Created by kemo on 01/06/2017.
 */
public class NumericTextField extends TextField {
    private boolean isNegative;
    public NumericTextField(String text) {
        super(text);
        init();
    }

    public NumericTextField()
    {
     init();
    }
    public void init()
    {
        textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 0) {
                     isNegative = (newValue.charAt(0) == '-') ?  true : false;
                    if (isNegative) setText("-" + newValue.replaceAll("[^\\d.]", ""));
                    else setText(newValue.replaceAll("[^\\d.]", ""));
                }
            }
        });
    }
}
