package drawingSoftware.Testing;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import drawingSoftware.Model;
import drawingSoftware.Tool.EllipseTool;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;

/*
 * EllipseToolResizeTest main is to test EllipseTool that resizes ellipse.
 * There are 3 case:
 * 1 - user set only Width field and ellipse must maintain ratio.
 * 2 - user set only height field and ellipse must maintain ratio.
 * 3 - user set both text fields and resized ellipse assumes indicating value. 
 * 
 */

public class EllipseToolResizeTest {
    
    Model model;
    Pane drawingWindow;
    EllipseTool ellipseTool;
    Ellipse ellipse;

    @Before 
    public void setUp(){
        final JFXPanel fxPanel = new JFXPanel(); 
        model = Model.getInstance();
        drawingWindow = new Pane();
        drawingWindow.setPrefSize(956, 800);
        ellipse = new Ellipse(422, 567);
        ellipseTool = new EllipseTool(ellipse, model, drawingWindow);
    }

    @Test 
    public void testResizeEllipse(){
        double newRadiusX = 400.22;
        double newRadiusY = 322.00;
        ellipseTool.setNewDimShape(ellipse.getRadiusX(), ellipse.getRadiusY(), newRadiusX, newRadiusY);
        Ellipse ellipseResized = new Ellipse(400.22, 322.00);
        assertEquals(ellipseResized.getRadiusX(), ellipseTool.getWidthEllipse(), 0.2);
    }
    @Test
    public void testResizeEllipseChangeWight(){
        double newRadiusX = 123.22;
        ellipseTool.setNewDimShape(ellipse.getRadiusX(), ellipse.getRadiusY(), newRadiusX, ellipse.getRadiusY());
        Ellipse ellipseResized = new Ellipse(123.22, 165.56);
        assertEquals(ellipseResized.getRadiusX(), ellipseTool.getWidthEllipse(), 0.2);
    }
    @Test
    public void testResizeEllipseChangeHeight(){
        double newRadiusY = 233.78;
        ellipseTool.setNewDimShape(ellipse.getRadiusX(), ellipse.getRadiusY(), ellipse.getRadiusX(), newRadiusY);
        Ellipse ellipseResized = new Ellipse(174, 233.78);
        assertEquals(ellipseResized.getRadiusX(), ellipseTool.getWidthEllipse(), 0.2);
    }
    
}
