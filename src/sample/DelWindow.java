package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Created by NoRFolf on 23.03.2016.
 */
public class DelWindow {

    private SecondaryWindow delWindow;
    private TableView<BookModel> delData = new TableView<>();

    void display(){
        delWindow = new SecondaryWindow("Delete info", "Delete", 300, 400);
        delWindow.view();
        delWindow.unbtn.setOnAction(e ->{
            delWindow.BtnCliked();
            delData.setItems(delWindow.getAllData().getItems());
            Controller.table.getItems().removeAll(delData.getItems());
            delWindowMassage(delWindow.getQuantity()
            );
        });
    }


    void delWindowMassage(Integer quantity){
        Stage delMassage = new Stage();
        VBox layout = new VBox(30);
        Label info  = new Label();
        Button okBtn = new Button("Ok");
        delMassage.setTitle("Delete massage");
        if (quantity != 0){
            info.setText("Items successfully deleted! Number of items: " + quantity);
        }else{
            info.setText("No items was deleted.");
        }
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(info, okBtn);
        okBtn.setOnAction(e -> delMassage.close());
        delMassage.setScene(new Scene(layout, 300, 100));
        delMassage.show();
    }
}
