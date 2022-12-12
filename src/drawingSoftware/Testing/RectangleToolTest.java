package drawingSoftware.Testing;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import drawingSoftware.Controller;
import drawingSoftware.Model;
import drawingSoftware.Tool.EllipseTool;
import drawingSoftware.Tool.RectangleTool;
import drawingSoftware.Tool.ShapeTool;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;



public class RectangleToolTest {
    
    Controller controller;
    Model model;
    Pane drawingWindow;
    ColorPicker borderColor;
    ColorPicker fillColor;
    RectangleTool rectangleTool;
    Rectangle rectangle;
    ShapeTool shapeTool;


    @Before
    public void setUp(){
        final JFXPanel fxPanel = new JFXPanel();  
        controller = null;
        model = Model.getInstance();
        drawingWindow = new Pane();
        drawingWindow.setPrefSize(200, 200);
        borderColor = new ColorPicker(Color.BLACK);
        fillColor = new ColorPicker(Color.YELLOW);
        rectangleTool = new RectangleTool(controller, model, drawingWindow, borderColor, fillColor);
        
    
    }  

    @Test
    public void testCreateRectangle(){
        rectangle = new Rectangle(200,400);
        this.rectangleTool.setRectangle(rectangle);
        assertEquals(rectangle, rectangleTool.getRectangle());
        
    }
    @Test
    public void testAddEllipseDrawingWindow(){
        rectangle = new Rectangle(200, 400);
        drawingWindow.getChildren().add(rectangle);
        assertEquals(rectangle, rectangleTool.getDrawingWindow().getChildren().remove(0));
    }   

    
}
