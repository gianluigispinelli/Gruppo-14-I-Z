package drawingSoftware.Testing.CommandTest.BackupCommandTest.EditorCommandTest;

import org.junit.*;

import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.EditorCommand.CutCommand;
import drawingSoftware.Editor.Editor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;

import static org.junit.Assert.*;

public class CutCommandTest {

    CutCommand cutCommand; 
    Model model; 
    Editor editor; 
    ObservableList<Node> backup; 

    @Before
    public void setUp(){
        model = Model.getInstance(); 
        editor = new Editor(model);
        this.cutCommand = new CutCommand(editor);
    }

    @Test
    public void testExecute(){

        /*

         * Cut functionality tested in Editor. Here we're assuring that the CutCommand saves a backup
         * of the model before executing the cut operation. 
         * 
         * Test cases: 
         * 1) cutting a pentagon
         * 2) cutting an ellipse (representative for Line and Rectangle)
         * 3) cutting a null figure
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
        cutCommand.execute(); 
        assertEquals(cutCommand.getBackup(), backup);

        /*

         * 2)  
         */

        Ellipse ellipse = new Ellipse(10.0,10.0,10.0,10.0); 
        model.addShape(ellipse);
        model.setSelectedShape(ellipse);
        backup = FXCollections.observableArrayList(editor.getModel().getAllShapes());
        cutCommand.execute();
        assertEquals(cutCommand.getBackup(), backup);

         /*

          * 3) 
          */

        model.setSelectedShape(null);
        backup = FXCollections.observableArrayList(editor.getModel().getAllShapes());
        cutCommand.execute();
        assertEquals(cutCommand.getBackup(), backup);        
    }

    
}
