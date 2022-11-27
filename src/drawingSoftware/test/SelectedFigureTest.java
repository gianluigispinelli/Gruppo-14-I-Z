package drawingSoftware.test;

import static org.junit.Assert.*;
import org.junit.*;
import drawingSoftware.SegmentState;
import drawingSoftware.EllipseState;
import drawingSoftware.RectangleState;
import drawingSoftware.SelectedFigure;
import drawingSoftware.State;

public class SelectedFigureTest {
    
    private SelectedFigure state;

    @Before
    public void setUp(){
        state = new SelectedFigure(new SegmentState());    
    }
    
    @Test
    public void initialStateTest(){
        assertTrue("initial State Test: ", state.getState() instanceof SegmentState);
    }

     
    @Test
     public void changeStateToRectangleTest(){
        state.changeState(new RectangleState());
        assertTrue("Change state test: ", state.getState() instanceof RectangleState);
    }
    @Test
    public void changeStateToEllipseTest(){
        state.changeState(new EllipseState());
        assertTrue("Change state test: ", state.getState() instanceof EllipseState);
    }
}

