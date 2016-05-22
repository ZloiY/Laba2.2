package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.File;


public class Main extends Application {
    public  TextField numPages;
    public static TextField numrows;
    public App fileChooser;
    File file;
    Controller controller = new Controller();
    SettingWindow setting = new SettingWindow();
    FileChoose fileChoose = new FileChoose();
    Stage window;
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        fileChooser = new App();
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(0, 0, 20, 0));
        HBox buttomBox = new HBox(20);
        Label numberOfElements = new Label();
        Menu fileMenu = new Menu("File...");
        MenuItem openMenu = new MenuItem("Open...");
        MenuItem save = new MenuItem("Save");
        fileMenu.getItems().addAll(openMenu, save);
        fileChooser.setWindow(window);
       /* openMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, (final MouseEvent mouseEvent) ->{
            fileChooser.setWindow();
            file = fileChooser.getFile();
            XmlParse parse = new XmlParse();
            parse.parse(file);
            numberOfElements.setText("Number of all elements:" +
                    Integer.toString(Controller.table.getItems().size()));
        });*/
        openMenu.setOnAction(e -> {
            file = fileChooser.getFileFunction();
            XmlParse parse = new XmlParse();
            parse.parse(file);
            numberOfElements.setText("Number of all elements:" +
                             Integer.toString(Controller.table.getItems().size()));

        });
        buttomBox.setAlignment(Pos.CENTER);
        layout.setCenter(controller.displayTable());
        numPages = new TextField("1");
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
        buttomBox.getChildren().addAll(addBtn, delBtn, findBtn,
                setting.view(Controller.table, setting.allData ,Integer.parseInt(numPages.getText()), setting.getAllPages()));
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);
        layout.setTop(menuBar);
        layout.setBottom(buttomBox);
        window.setTitle("Laba 2");
        window.setScene(new Scene(layout, 650, 400));
        window.show();
    }

//    private File getFile(){
//        fileChooser.setWindow(window);
//        return ((file != null) && (file.isFile()) ? file:null);
//    }

//    Thread myThread = new Thread(new Runnable() {
//        public void run() {
//            getFile();
//        }
//    });

    public static void main(String[] args) {
        launch(args);
    }
}
