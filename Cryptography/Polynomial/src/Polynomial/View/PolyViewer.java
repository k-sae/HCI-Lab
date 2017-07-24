package Polynomial.View;

import Polynomial.Polynomial;
import javafx.scene.layout.HBox;

/**
 * Created by kemo on 24/07/2017.
 */
public class PolyViewer extends HBox {
    Polynomial polynomial;
    public PolyViewer(Polynomial polynomial)
    {
        this.polynomial = polynomial;
    }
    public void setPolynomial(Polynomial polynomial)
    {
        this.polynomial = polynomial;
        //TODO
    }
    private void updateUi()
    {

    }

}
