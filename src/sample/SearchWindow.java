package sample;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;

/**
 * Created by NoRFolf on 27.03.2016.
 */
public class SearchWindow{
    private SecondaryWindow fndWindow;
    private TableView<BookModel> fndData = new TableView<>();
    private SettingWindow setting = new SettingWindow();

    void display(){
        fndWindow = new SecondaryWindow("Search", "Find", 300, 800);
        fndWindow.view();
        fndData.getColumns().addAll(Controller.table.getColumns());
        fndWindow.layout.setRight(fndData);
        fndWindow.unbtn.setOnAction(e ->{
            fndWindow.BtnCliked();
            fndData.setItems(fndWindow.getAllData().getItems());
            setting.pages(fndData, fndWindow.getAllData());
        });
    }

}
