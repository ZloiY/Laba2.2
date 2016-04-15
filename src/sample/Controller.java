package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by NoRFolf on 13.03.2016.
 */
public class Controller {
    public static  TableView<BookModel> table = new TableView<>();
    public static ObservableList<BookModel> tbl = FXCollections.observableArrayList();
    public TableView displayTable(){

        TableColumn<BookModel, String> bknmClmn = new TableColumn<>("Book Name");
        bknmClmn.setCellValueFactory(new PropertyValueFactory<>("nameBook"));
        TableColumn<BookModel, String> authorClmn = new TableColumn<>("Author");
        authorClmn.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<BookModel, String> pblsClmn = new TableColumn<>("Publisher");
        pblsClmn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        TableColumn<BookModel, Integer> numbbkClmn = new TableColumn<>("Number Book");
        numbbkClmn.setCellValueFactory(new PropertyValueFactory<>("numberBook"));
        TableColumn<BookModel, Integer> editClmn = new TableColumn<>("Edition");
        editClmn.setCellValueFactory(new PropertyValueFactory<>("edition"));
        TableColumn<BookModel, Integer> allClmn = new TableColumn<>("All");
        allClmn.setCellValueFactory(new PropertyValueFactory<>("all"));
        table.setItems(tbl);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(bknmClmn, authorClmn, pblsClmn, numbbkClmn, editClmn, allClmn);
        table.setEditable(false);
        return table;
    }

    public static void setTable(TableView<BookModel> newTable){
        table.setItems(newTable.getItems());
    }

}
