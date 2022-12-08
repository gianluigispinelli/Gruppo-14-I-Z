package drawingSoftware.Testing.CommandTest.BackupCommandTest;

import org.junit.*;

import drawingSoftware.Controller;
import drawingSoftware.Model;
import drawingSoftware.Command.CommandHistory;
import drawingSoftware.Command.BackupCommand.UndoCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.CopyCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.CutCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.DeleteCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.PasteCommand;
import drawingSoftware.Command.BackupCommand.ShapeCommand.DrawShapeCommand;
import drawingSoftware.Editor.Editor;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import static org.junit.Assert.*;

public class UndoCommandTest {

    UndoCommand undoCommand;
    Controller c;

    @Before
    public void setUp(){
        c = new Controller(); 
        undoCommand = new UndoCommand(c);
        c.setModel(new Model());
        c.setEditor(new Editor(c.getModel()));
        c.setCommandHistory(new CommandHistory());
    }

    @Test
    public void TestUndoCommand(){  /* test del costruttore */
        assertEquals(c, undoCommand.getController());
    }

    @Test
    public void testExecute(){

        /*

         * Test cases:
         *  1) undo after a copy-paste operation
         *  2) undo after a cut operation
         *  3) undo after a drawing operation
         *  4) undo after a delete operation
         *  these test cases verify for these operation that they save the state of the model before executing their
         *  operation 
         */


        /*

         * 1)
         */
        c.getModel().setSelectedShape(new Rectangle(10,10,10,10));
        CopyCommand copyCommand = new CopyCommand(c.getEditor());
        c.executeCommand(copyCommand);
        PasteCommand pCommand = new PasteCommand(c.getEditor()); 
        c.executeCommand(pCommand);
        undoCommand.execute();
        /* check if after the undo operation the shape is no more selected */
        assertEquals(c.getModel().getSelectedShape(), null);
        /* check if the state of the model is the state of the last executed command */
        assertEquals(c.getModel().getAllShapes(), pCommand.getBackup());    

        /*

         * 2) 
         */

        CutCommand cutCommand = new CutCommand(c.getEditor());
        c.executeCommand(cutCommand);
        undoCommand.execute();
        /* check if after the undo operation the shape is no more selected */
        assertEquals(c.getModel().getSelectedShape(), null);
        /* check if the state of the model is the state of the last executed command */
        assertEquals(c.getModel().getAllShapes(), cutCommand.getBackup());  

        /*

         * 3)  
         */

        DrawShapeCommand drawShapeCommand = new DrawShapeCommand(c.getModel(), new Ellipse(10,10,10,10));
        c.executeCommand(drawShapeCommand);
        undoCommand.execute();
        /* check if after the undo operation the shape is no more selected */
        assertEquals(c.getModel().getSelectedShape(), null);
        /* check if the state of the model is the state of the last executed command */
        assertEquals(c.getModel().getAllShapes(), drawShapeCommand.getBackup());  

        /*

         * 4) 
         */
        DeleteCommand deleteCommand = new DeleteCommand(c.getEditor());
        c.executeCommand(deleteCommand);
        undoCommand.execute();
        /* check if after the undo operation the shape is no more selected */
        assertEquals(c.getModel().getSelectedShape(), null);
        /* check if the state of the model is the state of the last executed command */
        assertEquals(c.getModel().getAllShapes(), deleteCommand.getBackup());  

    }
    
}
