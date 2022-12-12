package drawingSoftware.Testing;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import drawingSoftware.Controller;
import drawingSoftware.Model;
import drawingSoftware.Tool.EllipseTool;
import drawingSoftware.Tool.ShapeTool;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class EllipseToolTest {
    
    Controller controller;
    Model model;
    Pane drawingWindow;
    ColorPicker borderColor;
    ColorPicker fillColor;
    EllipseTool ellipseTool;
    Ellipse ellipse;
    ShapeTool shapeTool;

    /*
     * EllipseToolResizeTest main is to test EllipseTool create correct ellipse figure 
     * and adding it to the drawable window. 
     */

    @Before
    public void setUp(){
        final JFXPanel fxPanel = new JFXPanel();  
        controller = null;
        model = Model.getInstance();
        drawingWindow = new Pane();
        drawingWindow.setPrefSize(200, 200);
        borderColor = new ColorPicker(Color.BLACK);
        fillColor = new ColorPicker(Color.YELLOW);
        ellipseTool = new EllipseTool(controller, model, drawingWindow, borderColor, fillColor);
        
    
    }  

    @Test
    public void testCreateEllipse(){
        ellipse = new Ellipse(200,400);
        this.ellipseTool.setEllipse(ellipse);
        assertEquals(ellipse, ellipseTool.getEllipse());
        
    }
    @Test
    public void testAddEllipseDrawingWindow(){
        ellipse = new Ellipse(200, 400);
        drawingWindow.getChildren().add(ellipse);
        assertEquals(ellipse, ellipseTool.getDrawingWindow().getChildren().remove(0));
    }   

    
}
