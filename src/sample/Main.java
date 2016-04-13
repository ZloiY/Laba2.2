package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
    public  TextField numPages;
    public static TextField numrows;
    Controller controller = new Controller();
    SettingWindow setting = new SettingWindow();
    FileChoose fileChoose = new FileChoose();
    public void start(Stage primaryStage) throws Exception{
        Stage window = primaryStage;
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(0, 0, 20, 0));
        HBox buttomBox = new HBox(20);
        HBox pagesBox = new HBox(10);
        Label numberOfElements = new Label();
        VBox btn1 = new VBox(10);
        btn1.setAlignment(Pos.CENTER);
        VBox btn2 = new VBox(10);
        VBox btn3 = new VBox(10);
        btn3.setAlignment(Pos.CENTER);
        btn2.setAlignment(Pos.CENTER);
        Text allPg = new Text();
        Menu fileMenu = new Menu("File...");
        MenuItem openMenu = new MenuItem("Open...");
        MenuItem save = new MenuItem("Save");
        fileMenu.getItems().addAll(openMenu, save);
        openMenu.setOnAction(e -> {
            fileChoose.openFile(window);
            XmlParse parse = new XmlParse();
            parse.parse(fileChoose.file);
            numberOfElements.setText("Number of all elements:" +
                    Integer.toString(Controller.table.getItems().size()).toString());
        });
        Button frstPg = new Button("First page");
        Button lstPg = new Button("Last Page");
        frstPg.setOnAction(e -> setting.firstPage(Controller.table, setting.allData));
        lstPg.setOnAction(e -> setting.lastPage(Controller.table, setting.allData));
        buttomBox.setAlignment(Pos.CENTER);
        layout.setCenter(controller.displayTable());
        numPages = new TextField("1");
        numPages.setMaxWidth(30.0);
        Label division = new Label("/");
        pagesBox.getChildren().addAll(numPages, division, allPg);
        Button nextPg = new Button(">>");
        nextPg.setMinHeight(250);
        Button prevPg = new Button("<<");
        prevPg.setMinHeight(250);
        layout.setRight(nextPg);
        layout.setLeft(prevPg);
        nextPg.setOnAction(e -> {
            Integer next = Integer.parseInt(numPages.getText());
            next++;
            numPages.setText(next.toString());
            setting.pages(Controller.table, setting.allData, Integer.parseInt(numPages.getText()));
        });
        prevPg.setOnAction(e ->{
            Integer prev = Integer.parseInt(numPages.getText());
            prev--;
            numPages.setText(prev.toString());
            setting.pages(Controller.table, setting.allData, Integer.parseInt(numPages.getText()));
        });
        Button addBtn = new Button("Add element");
        addBtn.setOnAction(e -> {
            AddWindow editWindow = new AddWindow();
            editWindow.window();
        });
        Button delBtn= new Button("Delete");
        delBtn.setOnAction(e -> {
            DelWindow delWindow = new DelWindow();
            delWindow.display();
        });
        Button findBtn = new Button("Search");
        findBtn.setOnAction(e -> {
            SearchWindow searchWindow = new SearchWindow();
            searchWindow.display();
        });
        save.setOnAction(e -> fileChoose.saveFile(window));
        btn1.getChildren().addAll(addBtn, frstPg);
        btn2.getChildren().addAll(delBtn, lstPg);
        btn3.getChildren().addAll(pagesBox, numberOfElements);
        buttomBox.getChildren().addAll(btn1, btn2, findBtn, display(), btn3);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);
        layout.setTop(menuBar);
        layout.setBottom(buttomBox);
        window.setTitle("Laba 2");
        window.setScene(new Scene(layout, 650, 400));
        window.show();
    }
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
        applyBtn.setOnAction(e -> {
            setting.action(Controller.table, setting.allData, Double.parseDouble(numrows.getText()),
                    Integer.parseInt(numPages.getText()));
        });
        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
