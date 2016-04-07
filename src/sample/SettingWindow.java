package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * Created by NoRFolf on 05.04.2016.
 */
public class SettingWindow {

    public TextField numrows;
    public Integer allPages = 1;
    public ObservableList<BookModel> curPage;

    public void display(){
        Stage window = new Stage();
        window.setTitle("Settings");
        BorderPane mainPane = new BorderPane();
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20));
        Label label = new Label("Enter number of rows");
        numrows = new TextField();
        Button applyBtn = new Button("Apply");
        Button cnclBtn = new Button("Cancel");
        grid.add(label, 0, 0);
        grid.add(numrows, 0, 1);
        HBox btnBox = new HBox(10);
        btnBox.getChildren().addAll(applyBtn, cnclBtn);
        grid.add(btnBox, 0, 2);
        mainPane.setCenter(grid);
        window.setScene(new Scene(mainPane, 300, 200));
        applyBtn.setOnAction(e ->{
            Controller.table.setFixedCellSize((Controller.table.getHeight() - 27) / Double.parseDouble(numrows.getText()));
            Controller.table.setMaxHeight(Math.ceil(Controller.table.getFixedCellSize()
                    * Double.parseDouble(numrows.getText())));
            Controller.table.setMinHeight(Math.ceil(Controller.table.getFixedCellSize()
                    * Double.parseDouble(numrows.getText())));
            pages();
        });
        cnclBtn.setOnAction(e -> window.close());
        window.show();
    }

    public void pages(){
        allPages = Controller.table.getItems().size() / Integer.parseInt(numrows.getText());
        curPage = FXCollections.observableArrayList();
        Integer i = Integer.parseInt(Main.numPages.getText());
        if (i.equals(1)){
            Controller.table.setItems(XmlParse.allTable);
            curPage.clear();
            for(i = 1; i-1 < Integer.parseInt(numrows.getText()); i++){
                curPage.add(Controller.table.getItems().get(i));
            }
            Controller.table.setItems(curPage);
        }else {
            if (i > 1) {
                Controller.table.setItems(XmlParse.allTable);
                curPage.clear();
                i--;
                int j;
                for (i = i*Integer.parseInt(numrows.getText()), j = i; i < j * Integer.parseInt(Main.numPages.getText())
                        && i < Controller.table.getItems().size(); i++) {
                    curPage.add(Controller.table.getItems().get(i));
                }
                Controller.table.setItems(curPage);
            }
        }
    }

    public void firstPage(){
        Controller.table.setItems(XmlParse.allTable);
        curPage.clear();
        for(int i = 1; i-1 < Integer.parseInt(numrows.getText()); i++){
            curPage.add(Controller.table.getItems().get(i));
        }
        Controller.table.setItems(curPage);
    }

    public void lastPage(){
        Controller.table.setItems(XmlParse.allTable);
        for (int a = 1; a < Controller.table.getItems().size(); a++){
            curPage.clear();
            int i = a;
            int j;
            for (i = i*Integer.parseInt(numrows.getText()), j = i; i < j * a
                    && i < Controller.table.getItems().size(); i++) {
                curPage.add(Controller.table.getItems().get(i));
            }
            if (i == Controller.table.getItems().size()){
                Controller.table.setItems(curPage);
                return;
            }
        }
    }

}
