package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
    public static TextField numPages;
    Controller controller = new Controller();
    SettingWindow setting = new SettingWindow();
    FileChoose fileChoose = new FileChoose();
    public void start(Stage primaryStage) throws Exception{
        Stage window = primaryStage;
        BorderPane layout = new BorderPane();
        HBox buttomBox = new HBox(60);
        HBox pagesBox = new HBox(10);
        Text allPg = new Text();
        Menu settingMenu = new Menu("Settings");
        Menu fileMenu = new Menu("File...");
        MenuItem rows = new MenuItem("Rows...");
        MenuItem frstPg = new MenuItem("Go to first page");
        MenuItem lstPg = new MenuItem("Go to last page");
        MenuItem openMenu = new MenuItem("Open...");
        MenuItem save = new MenuItem("Save");
        settingMenu.getItems().addAll(rows, frstPg, lstPg);
        fileMenu.getItems().addAll(openMenu, save);
        rows.setOnAction(e -> {
            setting.display();
        });
        openMenu.setOnAction(e -> {
            fileChoose.openFile(window);
            XmlParse parse = new XmlParse();
            parse.parse(fileChoose.file);
        });
        frstPg.setOnAction(e -> setting.firstPage());
        lstPg.setOnAction(e -> setting.lastPage());
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
            setting.pages();
        });
        prevPg.setOnAction(e ->{
            Integer prev = Integer.parseInt(numPages.getText());
            prev--;
            numPages.setText(prev.toString());
            setting.pages();
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
        buttomBox.getChildren().addAll(addBtn, delBtn, findBtn, pagesBox);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, settingMenu);
        layout.setTop(menuBar);
        layout.setBottom(buttomBox);
        window.setTitle("Laba 2");
        window.setScene(new Scene(layout, 600, 300));
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
