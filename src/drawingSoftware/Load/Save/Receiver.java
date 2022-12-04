package drawingSoftware.Load.Save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;

public class Receiver {
    
    public void loadOperation(FileChooser fc, Pane drawingWindow){
    // file chooser
    fc.setTitle("carica file");

    // prendo il path del file selezionato tramite la dialog
    File loadedFile = fc.showOpenDialog(new Stage());   // loadedFile è il path dove si trova l'immagine

    try {   // try catch perché l'immagine selezionata potrebbe non più esserci
        Image image = new Image(new FileInputStream(loadedFile), 700, 515, false, false);
        drawingWindow.getChildren().add(new ImageView(image));
        } catch (FileNotFoundException e1) {
        e1.printStackTrace();
        }
    }

 
    public void save(Pane screen){
        Stage stage =  new Stage();
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files", "*.PNG");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showSaveDialog(stage);
        if (file != null){
            try {
                WritableImage writableImage = new WritableImage((int)screen.prefWidth(0),(int) screen.prefHeight(0));
                screen.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage,"png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
    
    
}
