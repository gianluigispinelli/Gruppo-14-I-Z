package drawingSoftware.EditorTest;

import org.junit.*;

import drawingSoftware.Editor.Editor;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import static org.junit.Assert.*;

public class EditorTest {

    Editor e; 
    Pane drawingWindow; 
    Rectangle rectangleToBeCopied;      /* Rettangolo del test da copiare */
    Rectangle rectangleToBePasted;

    @Before
    public void setUp(){
        drawingWindow = new Pane();
        rectangleToBeCopied = new Rectangle();
        rectangleToBeCopied.setId("selectedShape");
        rectangleToBePasted = new Rectangle();
        rectangleToBePasted.setX(10.0);       /* rettangolo incollato è di x=10 avanti rispetto alla figura copiata */
        rectangleToBePasted.setY(10.0);       /* rettangolo incollato è di y=10 avanti rispetto alla figura copiata */
        drawingWindow.getChildren().add(rectangleToBeCopied);       /* è il rettangolo selezionato nella scena */
      //  e = new Editor();
    }

    @Test
    public void testCopy(){
        /*

         *  BLACKBOX: Il metodo copy soddisfa il requisito nel momento in cui la figura viene copiata all'interno
         *  della variabile copiedShape della classe Editor 
         */
        e.copy();
        assertEquals(rectangleToBeCopied, e.getCopiedShape());      /* verifico che rettangolo è stato copiato */
    }

    /*
     * TODO: TRASFORMARE QUESTO TEST IN UN WHITE BOX PER GARANTIRE BRANCH COVERAGE
     */

    @Test
    public void testPaste(){

        e.copy();
        /*
        * Il metodo paste soddisfa le specifiche nel momento in cui 
        * la figura copiata dal metodo è quella che viene aggiunta al Pane drawing window 
        */
        e.paste();


        int pastedShapeIndex = drawingWindow.getChildren().size()-1;
        assertEquals(rectangleToBePasted, drawingWindow.getChildren().get(pastedShapeIndex));
    }

    @Test
    public void testCut(){
        e.cut();
        assertEquals(rectangleToBeCopied, e.getCopiedShape());      /* verifico che rettangolo è stato copiato */
    }

}
