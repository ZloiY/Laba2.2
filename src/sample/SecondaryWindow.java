package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by NoRFolf on 01.04.2016.
 */
public class SecondaryWindow {
    public TextField bknmTf = new TextField();
    public TextField athrTf = new TextField();
    public TextField pblsTf = new TextField();
    public TextField nmbbkUplimTf = new TextField();
    public TextField nmbbkLowlimitTf = new TextField();
    public TextField edtnUplimTf = new TextField();
    public TextField edtnLowlimTf = new TextField();
    public TextField allUplimTf = new TextField();
    public TextField allLowlimTf = new TextField();
    public BorderPane layout;
    public Stage window;
    public Button unbtn;


    SecondaryWindow(String titlename, String btnname, Integer height, Integer width){
        window = new Stage();
        window.setTitle(titlename);
        GridPane grid = new GridPane();
        HBox nmbbkhBox = new HBox(10);
        HBox edtnhBox = new HBox(10);
        HBox allhBox = new HBox(10);
        unbtn = new Button(btnname);
        Button canclBtn = new Button("Cancel");
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label bknmLbl = new Label("Enter book name:");
        grid.add(bknmLbl, 0, 0);
        Label athrLbl = new Label("Enter author name:");
        grid.add(athrLbl,0, 2);
        Label pblsLbl = new Label("Enter publisher name:");
        grid.add(pblsLbl, 0, 4);
        Label nmbbkLbl = new Label("Enter upper and lower limit of number book:");
        grid.add(nmbbkLbl, 1, 0);
        Label edtnLbl = new Label("Enter upper and lower limit of edition:");
        grid.add(edtnLbl, 1, 2);
        Label allLbl = new Label("Enter upper and lower limit of all book:");
        grid.add(allLbl, 1, 4);

        grid.add(bknmTf, 0, 1);
        grid.add(athrTf, 0, 3);
        grid.add(pblsTf, 0, 5);
        nmbbkUplimTf.setMaxWidth(50.0);
        nmbbkLowlimitTf.setMaxWidth(50.0);
        nmbbkhBox.getChildren().addAll(nmbbkUplimTf, nmbbkLowlimitTf);
        grid.add(nmbbkhBox, 1, 1);
        edtnUplimTf.setMaxWidth(50.0);
        edtnLowlimTf.setMaxWidth(50.0);
        edtnhBox.getChildren().addAll(edtnUplimTf, edtnLowlimTf);
        grid.add(edtnhBox, 1, 3);
        allUplimTf.setMaxWidth(50.0);
        allLowlimTf.setMaxWidth(50.0);
        allhBox.getChildren().addAll(allUplimTf, allLowlimTf);
        grid.add(allhBox, 1, 5);

        grid.add(unbtn, 0, 6 );
        grid.add(canclBtn, 1, 6);
        canclBtn.setOnAction(e -> window.close());
        layout = new BorderPane();
        layout.setCenter(grid);
        window.setScene(new Scene(layout, width, height));
    }

    void view(){
        window.show();
    }
}
