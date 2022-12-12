package drawingSoftware.Testing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;

/*
 * SliderZoomTestCase to test all possible value of sliderZoom.
 */

@RunWith(Parameterized.class)
public class SliderZoomTestCase {
    
    private SliderZoomTest sliderZoom;
    private double valueSlider;
    private double valueDrawing;
    
    public SliderZoomTestCase(double valueSlider, double valueDrawing){
        this.valueSlider = valueSlider;
        this.valueDrawing = valueDrawing;
    }

    @Before
    public void setUp(){
        sliderZoom = new SliderZoomTest();
        
    }

    @Parameterized.Parameters
    public static Collection valueSliders(){
        return Arrays.asList(new Object[][]{
            {0.5, 0.5},
            {1.0,1.0},
            {1.5,1.5},
            {2.0,2.0},
            {2.5, 2.5},
            {3.0, 3.0},
            {3.5,3.5},
            {4.0,4.0},
            {4.5,4.5},
            {5.0,5.0}
        });
    }

    @Test
    public void testValueSliderX(){
    
        assertEquals(valueDrawing, sliderZoom.valueSliderDrawingX(valueSlider), 0.3);
    }

    @Test
    public void testValueSliderY(){
        assertEquals(valueDrawing, sliderZoom.valueSliderDrawingY(valueSlider), 0.3);
    }
    

}
