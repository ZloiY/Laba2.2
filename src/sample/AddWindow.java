package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by NoRFolf on 13.03.2016.
 */
public class AddWindow {

    sample.BookModel item = new BookModel();
    TextField bknmTf = new TextField();
    TextField athrNmTf = new TextField();
    TextField athrSrNmTf = new TextField();
    TextField athrScNmTf = new TextField();
    TextField pblsTf = new TextField();
    TextField nmbbkTf = new TextField();
    TextField edtTf = new TextField();
    TextField allTf = new TextField();
    Stage window = new Stage();

    public void window(){

        window.setTitle("Enter information");

        GridPane grid = new GridPane();
        HBox athrNmBox = new HBox(10);
        athrNmBox.getChildren().addAll(athrNmTf, athrSrNmTf, athrScNmTf);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        athrNmBox.setMaxWidth(150);

        Label bknmLbl = new Label("Book Name:");
        grid.add(bknmLbl, 0, 0);
        Label athrLbl = new Label("Author:");
        grid.add(athrLbl, 0, 2);
        Label pblsLbl = new Label("Publisher:");
        grid.add(pblsLbl, 0, 4);
        Label nmbbkLbl = new Label("Number Book:");
        grid.add(nmbbkLbl, 1, 0);
        Label edtLbl = new Label("Edition:");
        grid.add(edtLbl, 1, 2);
        Label allLbl = new Label("All");
        grid.add(allLbl, 1 ,4);

        bknmTf.setPromptText("Enter book name");
        grid.add(bknmTf, 0, 1);
        grid.add(athrNmBox, 0, 3);
        pblsTf.setPromptText("Enter publisher name");
        grid.add(pblsTf, 0, 5);
        nmbbkTf.setPromptText("Enter book number");
        grid.add(nmbbkTf, 1, 1);
        edtTf.setPromptText("Enter edition number");
        grid.add(edtTf, 1, 3);
        allTf.setPromptText("Enter all number");
        grid.add(allTf, 1, 5);

        HBox hBox = new HBox(10);
        grid.add(hBox, 0, 6);
        Button addBtn = new Button("Add info");
        addBtn.setOnAction(e -> addBtnClicked());
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> window.close());
        hBox.getChildren().addAll(addBtn, cancelBtn);
        window.setScene(new Scene(grid, 320, 240));
        window.show();
    }

    void addBtnClicked(){

        if (   bknmTf.getLength() > 0
                && athrNmTf.getLength() > 0
                && pblsTf.getLength() > 0
                && nmbbkTf.getLength() > 0
                && edtTf.getLength() > 0
                && allTf.getLength() >0){
            item.setNameBook(bknmTf.getText());
            item.setAthrName(athrNmTf.getText());
            item.setAthrSurName(athrSrNmTf.getText());
            item.setAthrSecndName(athrScNmTf.getText());
            item.setAuthor(athrNmTf.getText(), athrSrNmTf.getText(), athrScNmTf.getText());
            item.setPublisher(pblsTf.getText());
            item.setNumberBook(Integer.parseInt(nmbbkTf.getText()));
            item.setEdition(Integer.parseInt(edtTf.getText()));
            item.setAll(Integer.parseInt(allTf.getText()));
            Controller.table.getItems().add(item);
            window.close();
        }else{
            AlertBox.display("Warning!", "You must fill all empty lines!");
        }
    }
}
