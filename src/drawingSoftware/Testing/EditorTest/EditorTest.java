package drawingSoftware.Testing.EditorTest;

import org.junit.*;

import drawingSoftware.Model;
import drawingSoftware.Editor.Editor;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import static org.junit.Assert.*;


public class EditorTest {

    private Editor editor; 
    private Model model; 

    @Before
    public void setUp(){
        model = Model.getInstance();
        editor = new Editor(model);
    }

    @Test
    public void testCopy(){
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
          editor.copy();
          /* check if the copied shape is the one selected on the drawingWindow */
          assertEquals(editor.getModel().getSelectedShape(), editor.getClipboard());  
          /*
  
           * 2) 
           */
          Line l = new Line(10, 10, 10, 10);
          model.addShape(l);
          model.setSelectedShape(l);
          editor.copy();
          assertEquals(editor.getModel().getSelectedShape(), editor.getClipboard());
          /*
  
           * 3)
           */
          Ellipse e = new Ellipse(10,10,10,10);
          model.addShape(e);
          model.setSelectedShape(e);
          editor.copy();
          assertEquals(editor.getModel().getSelectedShape(), editor.getClipboard());
  
          /*
  
           * 4)  
           */
          model.setSelectedShape(null);
          editor.copy();
          assertEquals(editor.getModel().getSelectedShape(), editor.getClipboard());
          assertEquals(editor.getClipboard(), null);    /* check if the clipboard doesn't save anything when we haven't selected a shape */
    }

    @Test
    public void testPaste(){

        // /*

        //  * Test cases: 
        //  * 1) Pasting a polygon
        //  * 2) Pasting an ellipse
        //  * 3) Pasting null ? 
        //  */

        // /*

        //  * 1) 
        //  */
        // Polygon pentagon = new Polygon();
        // pentagon.getPoints().addAll(new Double[]{
        //     30.0,10.0,
        //     10.0,30.0,
        //     20.0,50.0,
        //     40.0,50.0,
        //     50.0,30.0,
        // });
        // model.addShape(pentagon);
        // model.setCurrentShape(pentagon);

        // editor.copy();
        // editor.paste();

        // assertEquals(editor.getClipboard(), pentagon);
        // TODO: fix bug-> there are some values of the copied shape which are not equal to the one copied
        // assertEquals(model.getLastShape(), editor.getClipboard());

    }

    @Test
    public void testCut(){

        /*

         * Test cases:
         * 1) Cutting a Polygon
         * 2) Cutting an Ellipse
         * 3) null figure
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
        editor.cut();
        assertEquals(editor.getClipboard(), pentagon);
        assertEquals(editor.getModel().getSelectedShape(), null);

         /*
 
         * 2) 
         */

        Ellipse e = new Ellipse(10.0,10.0,10.0,10.0);
        model.addShape(e);
        model.setSelectedShape(e);
        editor.cut();
        
        assertEquals(e, editor.getClipboard());
        assertEquals(editor.getModel().getSelectedShape(), null);
 
         /*
 
         * 3)
         */

        model.setSelectedShape(null);
        editor.cut();
        assertEquals(editor.getModel().getSelectedShape(), null);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testDelete(){
        /*

         *  Test cases: 
         *  1) deleting a polygon
         *  2) deleting an ellipse (representative for line and rectangle)
         *  3) deleting a null figure ? 
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
        editor.delete();
        assertNull(model.getSelectedShape());
        model.getLastShape(); /* i expect an indexOutOfBoundException because, after deleting the only figure
        inside the model, the method getLastShapes() when he access to the size() of the observablelist of the model
        it will find that index 0 is out of bound for a list of length 0 */

        /*

         * 2)  
         */
        Ellipse ell = new Ellipse(10.0,10.0,10.0,10.0);
        model.addShape(ell);        
        model.setSelectedShape(ell);
        editor.delete();
        assertNull(model.getSelectedShape());       
        model.getLastShape();

        /*

         * 3)  
         */
        model.setSelectedShape(null);
        editor.delete();
        assertNull(model.getSelectedShape());   /* assuring that nothing happens */
        assertNull(model.getAllShapes());
    }

    @Test
    public void testDelete2(){

    } 

}
