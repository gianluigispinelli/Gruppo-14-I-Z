package drawingSoftware.Testing.CommandTest.BackupCommandTest.EditorCommandTest;

import org.junit.*;

import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.EditorCommand.CopyCommand;
import drawingSoftware.Editor.Editor;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static org.junit.Assert.*;

public class CopyCommandTest {

    CopyCommand copyCommand; 
    Editor editor;
    Model model; 

    @Before
    public void setUp(){
        model = Model.getInstance(); 
        editor = new Editor(model);
        copyCommand = new CopyCommand(editor);
    }

    @Test
    public void testCopyCommand(){      /* testing constructor */
        assertEquals(copyCommand.getEditor(), editor);
    }

    @Test
    public void testExecute(){

        /*

         * Test cases:
         *  1) copying a rectangle
         *  2) copying a line
         *  3) copying an ellipse
         *  4) bondary case: null figure (when the users hasn't selected any shape)
         *  these test cases verify that:
         *   - after the copy operation in the variable clipboard of Editor the shape has been saved
         *   - the copy operation copies the selected figure and no others
         */

         /*

          * 1) 
          */
        Rectangle r = new Rectangle(10,10,10,10); 
        model.addShape(r);
        model.setSelectedShape(r);
        copyCommand.execute();   /* copy operation */
        /* check if the copied shape is the one selected on the drawingWindow */
        assertEquals(copyCommand.getEditor().getModel().getSelectedShape(), copyCommand.getEditor().getClipboard());  
        /* check if the copied shape is in the clipboard */
        assertEquals(copyCommand.getEditor().getClipboard(), r);    
        /*

         * 2) 
         */
        Line l = new Line(10, 10, 10, 10);
        model.addShape(l);
        model.setSelectedShape(l);
        copyCommand.execute();
        assertEquals(copyCommand.getEditor().getModel().getSelectedShape(), copyCommand.getEditor().getClipboard());
        assertEquals(copyCommand.getEditor().getClipboard(), l);    /* check if the copied shape is in the clipboard */

        /*

         * 3)
         */
        Ellipse e = new Ellipse(10,10,10,10);
        model.addShape(e);
        model.setSelectedShape(e);
        copyCommand.execute();
        assertEquals(copyCommand.getEditor().getModel().getSelectedShape(), copyCommand.getEditor().getClipboard());
        assertEquals(copyCommand.getEditor().getClipboard(), e);    /* check if the copied shape is in the clipboard */

        /*

         * 4)  
         */
        model.setSelectedShape(null);
        copyCommand.execute();
        assertEquals(copyCommand.getEditor().getModel().getSelectedShape(), copyCommand.getEditor().getClipboard());
        assertEquals(copyCommand.getEditor().getClipboard(), null);    /* check if the clipboard doesn't save anything when we haven't selected a shape */
    }
    
}
