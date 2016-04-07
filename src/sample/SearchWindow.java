package sample;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;

/**
 * Created by NoRFolf on 27.03.2016.
 */
public class SearchWindow{
    private FilteredList<BookModel> filteredList = new FilteredList<BookModel>(Controller.tbl, p-> true);
    private TableView<BookModel> allData = new TableView<>();
    private SortedList<BookModel> sortedData;
    private SecondaryWindow fndWindow;

    void display(){
        fndWindow = new SecondaryWindow("Search", "Find", 300, 800);
        fndWindow.view();
        allData.getColumns().addAll(Controller.table.getColumns());
        fndWindow.layout.setRight(allData);
        fndWindow.unbtn.setOnAction(e -> fndBtnCliked());
    }

    void fndBtnCliked(){
        filteredList.setPredicate(p -> {
            if (fndWindow.athrTf.getText().isEmpty()) {
                return false;
            }else{

                if (p.getAuthor().contains(fndWindow.athrTf.getText())) {
                    return true;
                }
            }

            if (fndWindow.athrTf.getText().isEmpty() && fndWindow.pblsTf.getText().isEmpty()){
                return false;
            }else{
                if (p.getAuthor().contains(fndWindow.athrTf.getText())
                        && p.getPublisher().contains(fndWindow.pblsTf.getText())){
                    return true;
                }
            }

            if (fndWindow.athrTf.getText().isEmpty()
                    && (fndWindow.edtnLowlimTf.getText().isEmpty() && fndWindow.edtnUplimTf.getText().isEmpty())){
                return false;
            }else{
                if (p.getAuthor().contains(fndWindow.athrTf.getText())
                        && p.getEdition() < Integer.parseInt(fndWindow.edtnUplimTf.getText())
                        && p.getEdition() > Integer.parseInt(fndWindow.edtnLowlimTf.getText())){
                    return true;
                }
            }

            if (fndWindow.bknmTf.getText().isEmpty()){
                return false;
            }else{
                if (p.getNameBook().contains(fndWindow.bknmTf.getText())){
                    return true;
                }
            }

            if (fndWindow.edtnUplimTf.getText().isEmpty() && fndWindow.edtnLowlimTf.getText().isEmpty()){
                return false;
            }else{
                if (p.getEdition() < Integer.parseInt(fndWindow.edtnUplimTf.getText())
                        && p.getEdition() > Integer.parseInt(fndWindow.edtnLowlimTf.getText())){
                    return true;
                }
            }

            if (fndWindow.allUplimTf.getText().isEmpty() && fndWindow.allLowlimTf.getText().isEmpty()){
                return false;
            }else{
                if (p.getAll() < Integer.parseInt(fndWindow.allUplimTf.getText())
                        && p.getAll() > Integer.parseInt(fndWindow.allLowlimTf.getText())){
                    return true;
                }
            }
            return false;
        });

        sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(Controller.table.comparatorProperty());
        allData.setItems(sortedData);
        allData.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

}
