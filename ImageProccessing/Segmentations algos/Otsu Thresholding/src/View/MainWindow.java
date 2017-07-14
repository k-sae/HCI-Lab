package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


/**
 * Created by billy on 2017-07-14.
 */
public class MainWindow extends VBox {
    public MainWindow(){
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(20));
        setSpacing(20);

        Label title = new Label("Otsu Thresholding");
        title.setFont(Font.font(30));

        Label uploadImgLbl = new Label("Choose an image:");
        uploadImgLbl.setFont(Font.font(20));

        Button uploadImgBtn = new Button("Open image");
        //TODO #Khaled
        // let the user upload image when clicking on the button
        uploadImgBtn.setOnAction(event -> {
            // Try to call a private function and implement the file chooser in it and return the image
            // then show the image and it's histogram, then show the apply button
        });

        HBox uploadImg = new HBox(20);
        uploadImg.setAlignment(Pos.CENTER);
        uploadImg.getChildren().addAll(uploadImgLbl, uploadImgBtn);

        getChildren().addAll(title, new Separator(), uploadImg);
    }
}
