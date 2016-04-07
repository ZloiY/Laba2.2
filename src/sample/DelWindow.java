package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by NoRFolf on 23.03.2016.
 */
public class DelWindow {

    private SecondaryWindow delWindow;
    private Integer quantity = 0;

    void display(){
        delWindow = new SecondaryWindow("Delete info", "Delete", 300, 400);
        delWindow.view();
        delWindow.unbtn.setOnAction(e -> delBtnCliked());
    }

    void delBtnCliked(){
        if (delWindow.athrTf.getLength() > 0){
            for (int i = 0; i < Controller.table.getItems().size(); i++){
                if (delWindow.athrTf.getText().equals(Controller.table.getItems().get(i).getAuthor())){
                    Controller.table.getItems().remove(i);
                    quantity++;
                }
            }
            delWindowMassage(quantity);
            quantity = 0;
        }

        if (delWindow.athrTf.getLength() > 0 && delWindow.pblsTf.getLength() > 0){
            for (int i = 0; i < Controller.table.getItems().size(); i++){
                if (delWindow.athrTf.getText().equals(Controller.table.getItems().get(i).getAuthor())
                        && delWindow.pblsTf.getText().equals(Controller.table.getItems().get(i).getPublisher())){
                    Controller.table.getItems().remove(i);
                    quantity++;
                }
            }
            delWindowMassage(quantity);
            quantity = 0;
        }

        if (delWindow.athrTf.getLength() > 0 && (delWindow.nmbbkLowlimitTf.getLength() > 0
                || delWindow.nmbbkUplimTf.getLength() > 0)){
            for (int i = 0; i < Controller.table.getItems().size(); i++){
                if (delWindow.athrTf.getText().equals(Controller.table.getItems().get(i).getAuthor())
                        &&(Integer.parseInt(delWindow.nmbbkLowlimitTf.getText()) >= Controller.table.getItems().get(i).getNumberBook()
                        || Integer.parseInt(delWindow.nmbbkUplimTf.getText()) <= Controller.table.getItems().get(i).getNumberBook())){
                    Controller.table.getItems().remove(i);
                    quantity++;
                }
            }
            delWindowMassage(quantity);
            quantity = 0;
        }

        if (delWindow.bknmTf.getLength() > 0){
            for (int i = 0; i < Controller.table.getItems().size(); i++){
                if (delWindow.bknmTf.getText().equals(Controller.table.getItems().get(i).getNameBook())){
                    Controller.table.getItems().remove(i);
                    quantity++;
                }
            }
            delWindowMassage(quantity);
            quantity = 0;
        }

        if (delWindow.edtnLowlimTf.getLength() > 0 || delWindow.edtnUplimTf.getLength() > 0){
            for (int i = 0; i < Controller.table.getItems().size(); i++){
                if (Integer.parseInt(delWindow.edtnLowlimTf.getText()) >= Controller.table.getItems().get(i).getEdition()
                        || Integer.parseInt(delWindow.edtnUplimTf.getText()) <= Controller.table.getItems().get(i).getEdition()){
                    Controller.table.getItems().remove(i);
                    quantity++;
                }
            }
            delWindowMassage(quantity);
            quantity = 0;
        }

        if (delWindow.allLowlimTf.getLength() > 0 || delWindow.allUplimTf.getLength() > 0){
            for (int i = 0; i < Controller.table.getItems().size(); i++){
                if (Integer.parseInt(delWindow.allLowlimTf.getText()) >= Controller.table.getItems().get(i).getAll()
                        || Integer.parseInt(delWindow.allUplimTf.getText()) <= Controller.table.getItems().get(i).getAll()){
                    Controller.table.getItems().remove(i);
                    quantity++;
                }
            }
            delWindowMassage(quantity);
            quantity = 0;
        }
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
