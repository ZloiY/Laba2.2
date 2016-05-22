package sample;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by NoRFolf on 05.04.2016.
 */
public class FileChoose{

    private Desktop desktop = Desktop.getDesktop();
    public FileChooser fileChooser = new FileChooser();
    public File file;
    XmlParse sv = new XmlParse();

    public void openFile(Stage window){
//        fileChoose = new App();
//        fileChoose.setWindow(window);
//        file = fileChoose.get
//        fileChooser.setTitle("Choose file");
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//        file = fileChooser.showOpenDialog(window);
//        try{
//            desktop.open(file);
//        }catch (IOException ex){
//            Logger.getLogger(
//                    FileChoose.class.getName()).log(
//                    Level.SEVERE, null, ex
//            );
//        }
    }

    public void saveFile(Stage window){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Table");
        File file = fileChooser.showSaveDialog(window);
        sv.toXml(file);
    }
}
