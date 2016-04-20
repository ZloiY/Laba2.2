package sample;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Created by NoRFolf on 27.03.2016.
 */
public class SearchWindow{
    private SecondaryWindow fndWindow;
    private TableView<BookModel> fndData = new TableView<>();
    private SettingWindow setting = new SettingWindow();
    public TextField numPage;

    void display(){
        fndWindow = new SecondaryWindow("Search", "Find", 300, 800);
        fndWindow.view();
        VBox tableInterface = new VBox(20);
        numPage = new TextField("1");
        fndWindow.layout.setRight(tableInterface);
        tableInterface.getChildren().add(fndData);
        fndWindow.unbtn.setOnAction(e ->{
            fndWindow.BtnCliked();
            fndData.getColumns().addAll(Controller.table.getColumns());
            fndData.setItems(fndWindow.getAllData().getItems());
            Integer pages = fndData.getItems().size() / Integer.parseInt(Main.numrows.getText())+1;
            setting.action(fndData, fndWindow.getAllData(), Double.parseDouble(Main.numrows.getText()),
                    Integer.parseInt(numPage.getText()));
            tableInterface.getChildren().addAll(setting.view(fndData, fndWindow.getAllData(), Integer.parseInt(numPage.getText()), pages));
        });
    }

    void nextBtnCliked(){
        Integer pageNumber = Integer.parseInt(numPage.getText());
        pageNumber++;
        numPage.setText(pageNumber.toString());
        setting.pages(fndData, fndWindow.getAllData(), pageNumber);
    }

    void prevBtnCliked(){
        Integer pageNumber = Integer.parseInt(numPage.getText());
        pageNumber--;
        numPage.setText(pageNumber.toString());
        setting.pages(fndData, fndWindow.getAllData(), pageNumber);
    }

}
