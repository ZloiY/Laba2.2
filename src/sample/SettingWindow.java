package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public TableView<BookModel> allData = Controller.table;
    public Integer numberofrows;

    public GridPane display(){
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
        applyBtn.setOnAction(e ->{
            Controller.table.setFixedCellSize((Controller.table.getHeight() - 27) / Double.parseDouble(numrows.getText()));
            Controller.table.setMaxHeight(Math.ceil(Controller.table.getFixedCellSize()
                    * Double.parseDouble(numrows.getText())));
            Controller.table.setMinHeight(Math.ceil(Controller.table.getFixedCellSize()
                    * Double.parseDouble(numrows.getText())));
            numberofrows = Integer.parseInt(numrows.getText());
            pages(Controller.table, allData);
        });
        return grid;
    }

    public void pages(TableView<BookModel> mainTable, TableView secondaryData){
        allPages = mainTable.getItems().size() / numberofrows;
        curPage = FXCollections.observableArrayList();
        Integer i = Integer.parseInt(Main.numPages.getText());
        if (i.equals(1)){
            mainTable.setItems(secondaryData.getItems());
            curPage.clear();
            for(i = 1; i-1 < numberofrows; i++){
                curPage.add(mainTable.getItems().get(i));
            }
            mainTable.setItems(curPage);
        }else {
            if (i > 1) {
                mainTable.setItems(secondaryData.getItems());
                curPage.clear();
                i--;
                int j;
                for (i = i*numberofrows, j = i; i < j * Integer.parseInt(Main.numPages.getText())
                        && i < mainTable.getItems().size(); i++) {
                    curPage.add(mainTable.getItems().get(i));
                }
                mainTable.setItems(curPage);
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
