package drawingSoftware.Testing;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import drawingSoftware.Controller;
import drawingSoftware.Model;
import drawingSoftware.Tool.LineTool;
import drawingSoftware.Tool.ShapeTool;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
 
public class LineToolTest {
    
    Controller controller;
    Model model;
    Pane drawingWindow;
    ColorPicker borderColor;
    LineTool lineTool;
    Line line;
    ShapeTool shapeTool;


    @Before
    public void setUp(){
        final JFXPanel fxPanel = new JFXPanel();  
        controller = null;
        model = Model.getInstance();
        drawingWindow = new Pane();
        drawingWindow.setPrefSize(200, 200);
        borderColor = new ColorPicker(Color.BLACK);
        lineTool = new LineTool(controller, model, drawingWindow, borderColor);

    }  

    @Test
    public void testCreateRectangle(){
        line = new Line(20, 20, 400, 400);
        this.lineTool.setLine(line);
        assertEquals(line, lineTool.getLine());
        
    }
    @Test
    public void testAddEllipseDrawingWindow(){
        line = new Line(40, 40, 231, 678);
        drawingWindow.getChildren().add(line);
        assertEquals(line, lineTool.getDrawingWindow().getChildren().remove(0));
    }   

    
}