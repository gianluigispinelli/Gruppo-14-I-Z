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
    Controller controller;

    @Before
    public void setUp(){
        controller = new Controller(); 
        undoCommand = new UndoCommand(controller);
        controller.setModel(Model.getInstance());
        controller.setEditor(new Editor(controller.getModel()));
        controller.setCommandHistory(new CommandHistory());
    }

    @Test
    public void TestUndoCommand(){  /* test del costruttore */
        assertEquals(controller, undoCommand.getController());
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
         *  operation.
         *  Every command extending the BackupCommand interface has a backup where it saves the state of the
         *  model before executing its own operation.
         *  We're gonna verify that throught this tests.
         */


        /*

         * 1)
         */
        controller.getModel().setSelectedShape(new Rectangle(10,10,10,10));
        CopyCommand copyCommand = new CopyCommand(controller.getEditor());
        controller.executeCommand(copyCommand);
        PasteCommand pCommand = new PasteCommand(controller.getEditor()); 
        controller.executeCommand(pCommand);
        undoCommand.execute();
        /* check if after the undo operation the shape is no more selected */
        assertEquals(controller.getModel().getSelectedShape(), null);
        /* check if the state of the model is the state of the last executed command */
        assertEquals(controller.getModel().getAllShapes(), pCommand.getBackup());    

        /*

         * 2) 
         */

        CutCommand cutCommand = new CutCommand(controller.getEditor());
        controller.executeCommand(cutCommand);
        undoCommand.execute();
        /* check if after the undo operation the shape is no more selected */
        assertEquals(controller.getModel().getSelectedShape(), null);
        /* check if the state of the model is the state of the last executed command */
        assertEquals(controller.getModel().getAllShapes(), cutCommand.getBackup());  

        /*

         * 3)  
         */

        DrawShapeCommand drawShapeCommand = new DrawShapeCommand(controller.getModel(), new Ellipse(10,10,10,10));
        controller.executeCommand(drawShapeCommand);
        undoCommand.execute();
        /* check if after the undo operation the shape is no more selected */
        assertEquals(controller.getModel().getSelectedShape(), null);
        /* check if the state of the model is the state of the last executed command */
        assertEquals(controller.getModel().getAllShapes(), drawShapeCommand.getBackup());  

        /*

         * 4) 
         */
        DeleteCommand deleteCommand = new DeleteCommand(controller.getEditor());
        controller.executeCommand(deleteCommand);
        undoCommand.execute();
        /* check if after the undo operation the shape is no more selected */
        assertEquals(controller.getModel().getSelectedShape(), null);
        /* check if the state of the model is the state of the last executed command */
        assertEquals(controller.getModel().getAllShapes(), deleteCommand.getBackup());  
    }
    
}
