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

    public void setAllData(){
        allData.setItems(Controller.table.getItems());
    }

    public Integer getAllPages(){return allPages;}

    public void action(TableView table, TableView allData, Double numrows, Integer numPages){
        table.setFixedCellSize(table.getHeight() / numrows);
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

    public GridPane view(TableView<BookModel> mainTable, TableView secondaryTable, Integer currPageIndex, Integer allPgs){
        GridPane mainLayout = new GridPane();
        HBox pgsBox = new HBox(10);
        HBox nextPrevBox = new HBox(10);
        HBox numrowsBox = new HBox(10);
        TextField currPg = new TextField();
        TextField numbofrows = new TextField("5");
        numbofrows.setMaxWidth(30);
        currPg.setMaxWidth(40);
        currPg.setText(currPageIndex.toString());
        Label allPg = new Label("/" + allPgs);
        Label txtFieldLbl = new Label("Enter number of rows:");
        Button nextBtn = new Button(">>");
        Button prevBtn = new Button("<<");
        Button frstPg = new Button("First Page");
        Button lstPg = new Button("Last Page");
        Button aplBtn = new Button("Apply");
        pgsBox.getChildren().addAll(currPg, allPg);
        nextPrevBox.getChildren().addAll(prevBtn, nextBtn);
        numrowsBox.getChildren().addAll(numbofrows,aplBtn);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.add(pgsBox, 0, 0);
        mainLayout.add(txtFieldLbl, 2, 0);
        mainLayout.add(numrowsBox, 2, 1);
        mainLayout.add(nextPrevBox, 1, 0);
        mainLayout.add(frstPg, 0, 1);
        mainLayout.add(lstPg, 1, 1);
        nextBtn.setOnAction(e -> {
            Integer nextPg = Integer.parseInt(currPg.getText()) + 1;
            currPg.setText(nextPg.toString());
            pages(mainTable, secondaryTable, nextPg);
        });
        prevBtn.setOnAction(e -> {
            Integer prevPg = Integer.parseInt(currPg.getText()) - 1;
            currPg.setText(prevPg.toString());
            pages(mainTable, secondaryTable, prevPg);
        });
        aplBtn.setOnAction(e -> {
            setAllData();
            Main.numrows = new TextField();
            Main.numrows.setText(numbofrows.getText());
            action(mainTable, secondaryTable, Double.parseDouble(Main.numrows.getText()), Integer.parseInt(currPg.getText()));
        });
        frstPg.setOnAction(e -> firstPage(mainTable, secondaryTable));
        lstPg.setOnAction(e -> lastPage(mainTable, secondaryTable));

        return mainLayout;
    }

}
