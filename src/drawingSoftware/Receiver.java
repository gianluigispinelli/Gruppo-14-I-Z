package drawingSoftware;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Receiver {

    public void loadOperation(FileChooser fc, GraphicsContext graphicsContext){
    // file chooser
    fc.setTitle("carica file");

    // prendo il path del file selezionato tramite la dialog
    File loadedFile = fc.showOpenDialog(new Stage());   // loadedFile è il path dove si trova l'immagine

    try {   // try catch perché l'immagine selezionata potrebbe non più esserci
        Image image = new Image(new FileInputStream(loadedFile), 700, 515, false, false);
        graphicsContext.drawImage(image, 0, 0);
        } catch (FileNotFoundException e1) {
    // TODO Auto-generated catch block
        e1.printStackTrace();
        }
    }
    
    
}
