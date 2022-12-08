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

    CopyCommand cc; 
    Editor e;
    Model m; 

    @Before
    public void setUp(){
        m = new Model(); 
        e = new Editor(m);
        cc = new CopyCommand(e);
    }

    @Test
    public void testCopyCommand(){      /* testing constructor */
        assertEquals(cc.getEditor(), e);
    }

    @Test
    public void testExecute(){

        /*

         * Test cases:
         *  1) copying a rectangle
         *  2) copying a line
         *  3) copying an ellipse
         *  4) bondary case: null figure (when the users hasn't selected anything)
         *  these test cases verify for these operation that they save the state of the model before executing their
         *  operation 
         */

         /*

          * 1) 
          */
        Rectangle r = new Rectangle(10,10,10,10); 
        m.setSelectedShape(r);
        cc.execute();   /* copy operation */
        assertEquals(m.getSelectedShape(), r);  /* check if the copied shape is the one selected on the drawingWindow */

        /*

         * 2) 
         */
        Line l = new Line(10, 10, 10, 10);
        m.setSelectedShape(l);
        cc.execute();
        assertEquals(m.getSelectedShape(), l);

        /*

         * 3)
         */
        Ellipse e = new Ellipse(10,10,10,10);
        m.setSelectedShape(e);
        cc.execute();
        assertEquals(m.getSelectedShape(), e);

        /*

         * 4)  
         */
        m.setSelectedShape(null);
        cc.execute();
        assertEquals(m.getSelectedShape(), null);
    }
    
}
