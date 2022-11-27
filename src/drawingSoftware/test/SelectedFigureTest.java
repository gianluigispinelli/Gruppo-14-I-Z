package drawingSoftware.test;

import static org.junit.Assert.*;
import org.junit.*;
import drawingSoftware.SegmentState;
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
     public void changeStateTest(){
        State expectedState = new SegmentState();
        state.changeState(expectedState);
        assertSame(expectedState, state.getState());
    }
}

