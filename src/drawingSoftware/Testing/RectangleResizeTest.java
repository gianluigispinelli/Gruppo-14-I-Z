package drawingSoftware.Testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import drawingSoftware.Model;
import drawingSoftware.Tool.RectangleTool;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;



public class RectangleResizeTest {
    Model model;
    Pane drawingWindow;
    RectangleTool rectangleTool;
    Rectangle rectangle;

    @Before 
    public void setUp(){
        final JFXPanel fxPanel = new JFXPanel(); 
        model = Model.getInstance();
        drawingWindow = new Pane();
        drawingWindow.setPrefSize(956, 800);
        rectangle = new Rectangle(422, 567);
        rectangleTool = new RectangleTool(rectangle, model, drawingWindow);
    }

    @Test 
    public void testResizeRectangle(){
        double newWidth = 400.22;
        double newHeight = 322.00;
        rectangleTool.setNewDimShape(rectangle.getWidth(), rectangle.getHeight(), newWidth, newHeight);
        Rectangle rectangleResized = new Rectangle(400.22, 322.00);
        assertEquals(rectangleResized.getWidth(), rectangleTool.getWidthRectangle(), 0.2);
    }
    @Test
    public void testResizeRectangleChangeWight(){
        double newWidth = 123.22;
        rectangleTool.setNewDimShape(rectangle.getWidth(), rectangle.getHeight(), newWidth, rectangle.getHeight());
        Rectangle rectangleResized = new Rectangle(123.22, 165.56);
        assertEquals(rectangleResized.getWidth(), rectangleTool.getWidthRectangle(), 0.2);
    }
    @Test
    public void testResizeRectangleChangeHeight(){
        double newHeight = 233.78;
        rectangleTool.setNewDimShape(rectangle.getWidth(), rectangle.getHeight(), rectangle.getWidth(), newHeight);
        Rectangle rectangleResized = new Rectangle(174, 233.78);
        assertEquals(rectangleResized.getWidth(), rectangleTool.getWidthRectangle(), 0.2);
    }
}
