package drawingSoftware.Testing.EditorTest;

import org.junit.*;

import drawingSoftware.Model;
import drawingSoftware.Editor.Editor;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

public class EditorTest {

    Editor editor; 
    Shape shapeClipboard; 

    @Before
    public void setUp(){
        Model model = new Model();
        editor = new Editor(model);

        Rectangle rect = new Rectangle(100,100,50,50);
        Ellipse ell = new Ellipse(100,100,50,50);
        Line line = new Line(100,100,50,50);


        model.addShape(rect); 
        model.addShape(line);
        model.addShape(ell);

        model.setCurrentShape(rect);
        shapeClipboard = rect; 

    }

    @Test
    public void testCopy(){
        /*

         *  BLACKBOX: Il metodo copy soddisfa il requisito nel momento in cui la figura viene copiata all'interno
         *  della variabile copiedShape della classe Editor 
         */

         /* checking if the copied shape by the editor is the one selected */
         editor.copy();
         assertEquals(editor.getModel().getSelectedShape(), editor.getClipboard());
    }

    /*
     * TODO: TRASFORMARE QUESTO TEST IN UN WHITE BOX PER GARANTIRE BRANCH COVERAGE
     */

    @Test
    public void testPaste(){

        /*
        * Il metodo paste soddisfa le specifiche nel momento in cui 
        * la figura copiata dal metodo è quella che viene aggiunta al Pane drawing window 
        */
        editor.copy();
        editor.paste();

        /* branch if clipboard istanceof Rectangle */
        Rectangle copied = (Rectangle)editor.getClipboard();
        copied.setX(copied.getX()+10);
        copied.setY(copied.getY()+10);
        copied.setWidth(copied.getWidth());
        copied.setHeight(copied.getHeight());
        copied.setStroke(copied.getStroke());
        copied.setFill(copied.getFill());

        assertEquals(copied, editor.getModel().getLastShape());

        /*
         * 
         * Fare gli altri casi
         * 
         * 
         */

    }

    @Test
    public void testCut(){
        editor.cut();
        assertEquals(shapeClipboard, editor.getClipboard()); /* check if the cutted shape is in the clipboard */
    }

}
