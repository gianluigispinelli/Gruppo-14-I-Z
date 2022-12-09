package drawingSoftware.Test;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import javafx.scene.shape.Ellipse;

public class ResizeEllipseTest {
    Ellipse ellipse;
    
    @Before
    public void setUp(){
        ellipse = new Ellipse();

    }
    @Test
    public void testEllipse(){
        assertTrue(ellipse instanceof Ellipse);

    }
    @Test
    public void testEllipseResize(){
        
    }
}
