package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by NoRFolf on 13.03.2016.
 */
public class AlertBox {

    public static void display(String windowName, String errorName){

        Stage window = new Stage();
        window.setTitle(windowName);
        window.initModality(Modality.APPLICATION_MODAL);
        Label massage = new Label(errorName);
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        Button okBtn = new Button("OK");
        okBtn.setOnAction(e -> window.close());
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setCenter(vBox);
        vBox.getChildren().addAll(massage, okBtn);
        window.setScene(new Scene(layout, 250, 100));
        window.show();
    }

}
