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

    public Integer allPages = 1;
    public ObservableList<BookModel> curPage;
    public TableView<BookModel> allData = new TableView<>();


    public void action(TableView table, TableView allData, Double numrows, Integer numPages){
        table.setFixedCellSize(table.getHeight() / numrows);
        allData.setItems(Controller.table.getItems());
        table.setMaxHeight(Math.ceil(table.getFixedCellSize()
                * numrows));
        table.setMinHeight(Math.ceil(table.getFixedCellSize()
                * numrows));
        pages(table, allData, numPages);
    }

    public void pages(TableView<BookModel> mainTable, TableView secondaryData, Integer numPages){
        allPages = mainTable.getItems().size() / Integer.parseInt(Main.numrows.getText());
        curPage = FXCollections.observableArrayList();
        Integer i = numPages;
        if (i.equals(1)){
            mainTable.setItems(secondaryData.getItems());
            curPage.clear();
            for(i = 1; i-1 < Integer.parseInt(Main.numrows.getText()); i++){
                curPage.add(mainTable.getItems().get(i));
            }
            mainTable.setItems(curPage);
        }else {
            if (i > 1) {
                mainTable.setItems(secondaryData.getItems());
                curPage.clear();
                i--;
                int j;
                for (i = i*Integer.parseInt(Main.numrows.getText()), j = i; i < j * numPages
                        && i < mainTable.getItems().size(); i++) {
                    curPage.add(mainTable.getItems().get(i));
                }
                mainTable.setItems(curPage);
            }
        }
    }

    public void firstPage(TableView<BookModel> mainTable, TableView secondaryTable){
        mainTable.setItems(secondaryTable.getItems());
        curPage.clear();
        for(int i = 1; i-1 < Integer.parseInt(Main.numrows.getText()); i++){
            curPage.add(Controller.table.getItems().get(i));
        }
        mainTable.setItems(curPage);
    }

    public void lastPage(TableView<BookModel> mainTable, TableView secondaryTable){
        mainTable.setItems(secondaryTable.getItems());
        for (int a = 1; a < mainTable.getItems().size(); a++){
            curPage.clear();
            int i = a;
            int j;
            for (i = i*Integer.parseInt(Main.numrows.getText()), j = i; i < j * a
                    && i < mainTable.getItems().size(); i++) {
                curPage.add(mainTable.getItems().get(i));
            }
            if (i == mainTable.getItems().size()){
                mainTable.setItems(curPage);
                return;
            }
        }
    }

}
