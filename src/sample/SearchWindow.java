package sample;

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
        HBox pagesInterface = new HBox(10);
        numPage = new TextField("1");
        numPage.setMaxWidth(30);
        Label allPage = new Label();
        Button nextBtn = new Button(">>");
        nextBtn.setOnAction(e -> nextBtnCliked());
        Button prevBtn = new Button("<<");
        Button frstPg = new Button("First Page");
        Button lastPg = new Button("Last Page");
        prevBtn.setOnAction(e -> prevBtnCliked());
        fndData.getColumns().addAll(Controller.table.getColumns());
        tableInterface.getChildren().addAll(fndData, pagesInterface);
        fndWindow.layout.setRight(tableInterface);
        fndWindow.unbtn.setOnAction(e ->{
            fndWindow.BtnCliked();
            fndData.setItems(fndWindow.getAllData().getItems());
            Double pages = Math.ceil(fndData.getItems().size() / Integer.parseInt(Main.numrows.getText()));
            allPage.setText(pages.toString());
            frstPg.setOnAction(event -> setting.firstPage(fndData, fndWindow.getAllData()));
            frstPg.setOnAction(event -> setting.lastPage(fndData, fndWindow.getAllData()));
            pagesInterface.getChildren().addAll(numPage, allPage, prevBtn, nextBtn, frstPg, lastPg);
            setting.action(fndData, fndWindow.getAllData(), Double.parseDouble(Main.numrows.getText()),
                    Integer.parseInt(numPage.getText()));
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
