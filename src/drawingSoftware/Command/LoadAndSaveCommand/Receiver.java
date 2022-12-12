package drawingSoftware.Command.LoadAndSaveCommand;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.RenderedImage;
import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.Arrays;

import javax.imageio.ImageIO;

import drawingSoftware.Model;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Receiver {
    
    public void loadOperation(Model model)throws IOException{
        Stage stage =  new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files", "*.XML");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showOpenDialog(stage);
    // file chooser
        try (XMLDecoder decoder = new XMLDecoder( new BufferedInputStream( new BufferedInputStream(Files.newInputStream(file.toPath()))))) {

            decoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
            });

            model.addAll(Arrays.asList((Node[]) decoder.readObject()));
            
        }
    }
 
    public void save(Pane drawingWindow) throws IOException{
        Stage stage =  new Stage();
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files", "*.XML");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showSaveDialog(stage);
        try (XMLEncoder encoder = new XMLEncoder( new BufferedOutputStream(Files.newOutputStream(file.toPath())))) {

            encoder.setExceptionListener(e -> {
                throw new RuntimeException(e);
        });

        encoder.setPersistenceDelegate(Color.class,new DefaultPersistenceDelegate( new String[] { "red", "green", "blue", "opacity" }));

        encoder.writeObject(drawingWindow.getChildren().toArray(new Node[0]));
        }
    }

    
    
}
