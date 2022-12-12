package drawingSoftware.Testing.CommandTest.BackupCommandTest.EditorCommandTest;

import org.junit.*;

import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.EditorCommand.CopyCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.PasteCommand;
import drawingSoftware.Editor.Editor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;

import static org.junit.Assert.*;

public class PasteCommandTest {

    PasteCommand pasteCommand; 
    CopyCommand copyCommand; 
    Model model; 
    Editor editor; 
    ObservableList<Node> backup; 

    @Before
    public void setUp(){
        model = Model.getInstance();
        editor = new Editor(model);
        pasteCommand = new PasteCommand(editor);
        copyCommand = new CopyCommand(editor);
    }

    @Test (expected = NullPointerException.class)
    public void testExecute(){

        /*

         * Paste functionality tested in Editor. Here we're assuring that the PasteCommand saves a backup
         * of the model before executing the paste operation. 
         * 
         * Test cases: 
         * 1) pasting a pentagon
         * 2) pasting an ellipse (representative for Line and Rectangle)
         * 3) pasting a null figure
         */


         /*

          * 1) 
          */
        Polygon pentagon = new Polygon();
        pentagon.getPoints().addAll(new Double[]{
            30.0,10.0,
            10.0,30.0,
            20.0,50.0,
            40.0,50.0,
            50.0,30.0,
        });
        model.addShape(pentagon);
        model.setSelectedShape(pentagon);

        backup = FXCollections.observableArrayList(editor.getModel().getAllShapes());
        copyCommand.execute(); 
        pasteCommand.execute(); 
        assertEquals(pasteCommand.getBackup(), backup);

        /*

         * 2)  
         */

        Ellipse ellipse = new Ellipse(10.0,10.0,10.0,10.0); 
        model.addShape(ellipse);
        model.setSelectedShape(ellipse);
        backup = FXCollections.observableArrayList(editor.getModel().getAllShapes());
        copyCommand.execute(); 
        pasteCommand.execute();
        assertEquals(pasteCommand.getBackup(), backup);

         /*

          * 3) 
          */

        model.setSelectedShape(null);
        backup = FXCollections.observableArrayList(editor.getModel().getAllShapes());
        copyCommand.execute(); 
        pasteCommand.execute();     /* I expect a nullPointerException since the clipboard is null */
    }    
}
