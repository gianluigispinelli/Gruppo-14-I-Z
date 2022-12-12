package drawingSoftware.Testing.CommandTest.BackupCommandTest.EditorCommandTest;

import org.junit.*;

import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.EditorCommand.DeleteCommand;
import drawingSoftware.Editor.Editor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;

import static org.junit.Assert.*;

public class DeleteCommandTest {

    DeleteCommand deleteCommand; 
    Model model; 
    Editor editor; 
    ObservableList<Node> backup; 

    @Before
    public void setUp(){
        model = Model.getInstance();
        editor = new Editor(model);
        deleteCommand = new DeleteCommand(editor);
    }

    @Test
    public void testExecute(){

        /*

         * Delete functionality tested in Editor. Here we're assuring that the deleteCommand saves a backup
         * of the model before executing the delete operation. 
         * 
         * Test cases: 
         * 1) deleting a pentagon
         * 2) deleting an ellipse (representative for Line and Rectangle)
         * 3) deleting a null figure
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
        deleteCommand.execute(); 
        assertEquals(deleteCommand.getBackup(), backup);

        /*

         * 2)  
         */

        Ellipse ellipse = new Ellipse(10.0,10.0,10.0,10.0); 
        model.addShape(ellipse);
        model.setSelectedShape(ellipse);
        backup = FXCollections.observableArrayList(editor.getModel().getAllShapes());
        deleteCommand.execute();
        assertEquals(deleteCommand.getBackup(), backup);

         /*

          * 3) 
          */

        model.setSelectedShape(null);
        backup = FXCollections.observableArrayList(editor.getModel().getAllShapes());
        deleteCommand.execute();
        assertEquals(deleteCommand.getBackup(), backup);        
    }    
}
