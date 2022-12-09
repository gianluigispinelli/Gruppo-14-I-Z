package drawingSoftware.Test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import drawingSoftware.SliderZoom;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

import static org.junit.Assert.*;

public class SliderZoomTest {

    @BeforeClass 
    public static void initGUI() {
        final JFXPanel fxPanel = new JFXPanel();   
    }
      
    SliderZoom sliderZoom;
    Slider slider;
    Pane drawingWindow;
    Label labelSliderZoom;
    
    
    @Before
    public void setUp(){
        slider = new Slider();
        sliderZoom = new SliderZoom(slider);
        drawingWindow = new Pane();
        drawingWindow.setPrefWidth(959);
        drawingWindow.setPrefHeight(526);
        labelSliderZoom = new Label();
        
    }
    @Test
    public void testSliderMinValue(){
        sliderZoom.getSlider().setValue(0.5);

        double resizeDrawingWindowX = drawingWindow.getPrefWidth()*0.5;
        double resizeDrawingWindowY = drawingWindow.getPrefHeight()*0.5;
        drawingWindow.setScaleX(sliderZoom.getSlider().getValue());
        double valueScaleX = drawingWindow.getScaleX();
        drawingWindow.setScaleY(sliderZoom.getSlider().getValue());
        double valueScaleY = drawingWindow.getScaleY();
        
        assertTrue(resizeDrawingWindowX/drawingWindow.getPrefWidth() == valueScaleX);
        assertTrue(resizeDrawingWindowY/drawingWindow.getPrefHeight() == valueScaleY);
        
    }
    @Test 
    public void testSliderDefaultValue(){
        sliderZoom.getSlider().setValue(1);
        double resizeDrawingWindowX = drawingWindow.getPrefWidth()*1;
        double resizeDrawingWindowY = drawingWindow.getPrefHeight()*1;

        drawingWindow.setScaleX(sliderZoom.getSlider().getValue());
        double valueScaleX = drawingWindow.getScaleX();
        drawingWindow.setScaleY(sliderZoom.getSlider().getValue());
        double valueScaleY = drawingWindow.getScaleY();
        
        assertTrue(resizeDrawingWindowX/drawingWindow.getPrefWidth() == valueScaleX);
        assertTrue(resizeDrawingWindowY/drawingWindow.getPrefHeight() == valueScaleY);
    }

    @Test 
    public void testSliderValueOnePointFive(){
        sliderZoom.getSlider().setValue(1.5);
        double resizeDrawingWindowX = drawingWindow.getPrefWidth()*1.5;
        double resizeDrawingWindowY = drawingWindow.getPrefHeight()*1.5;

        drawingWindow.setScaleX(sliderZoom.getSlider().getValue());
        double valueScaleX = drawingWindow.getScaleX();
        drawingWindow.setScaleY(sliderZoom.getSlider().getValue());
        double valueScaleY = drawingWindow.getScaleY();
        
        assertTrue(resizeDrawingWindowX/drawingWindow.getPrefWidth() == valueScaleX);
        assertTrue(resizeDrawingWindowY/drawingWindow.getPrefHeight() == valueScaleY);
    }

    @Test 
    public void testSliderValueTwo(){
        sliderZoom.getSlider().setValue(2);
        double resizeDrawingWindowX = drawingWindow.getPrefWidth()*2;
        double resizeDrawingWindowY = drawingWindow.getPrefHeight()*2;

        drawingWindow.setScaleX(sliderZoom.getSlider().getValue());
        double valueScaleX = drawingWindow.getScaleX();
        drawingWindow.setScaleY(sliderZoom.getSlider().getValue());
        double valueScaleY = drawingWindow.getScaleY();
        
        assertTrue(resizeDrawingWindowX/drawingWindow.getPrefWidth() == valueScaleX);
        assertTrue(resizeDrawingWindowY/drawingWindow.getPrefHeight() == valueScaleY);
    }

    @Test 
    public void testSliderValueTwoPointFive(){
        sliderZoom.getSlider().setValue(2.5);
        double resizeDrawingWindowX = drawingWindow.getPrefWidth()*2.5;
        double resizeDrawingWindowY = drawingWindow.getPrefHeight()*2.5;

        drawingWindow.setScaleX(sliderZoom.getSlider().getValue());
        double valueScaleX = drawingWindow.getScaleX();
        drawingWindow.setScaleY(sliderZoom.getSlider().getValue());
        double valueScaleY = drawingWindow.getScaleY();
        
        assertTrue(resizeDrawingWindowX/drawingWindow.getPrefWidth() == valueScaleX);
        assertTrue(resizeDrawingWindowY/drawingWindow.getPrefHeight() == valueScaleY);
    }
    @Test 
    public void testSliderValueThree(){
        sliderZoom.getSlider().setValue(3);
        double resizeDrawingWindowX = drawingWindow.getPrefWidth()*3;
        double resizeDrawingWindowY = drawingWindow.getPrefHeight()*3;

        drawingWindow.setScaleX(sliderZoom.getSlider().getValue());
        double valueScaleX = drawingWindow.getScaleX();
        drawingWindow.setScaleY(sliderZoom.getSlider().getValue());
        double valueScaleY = drawingWindow.getScaleY();
        
        assertTrue(resizeDrawingWindowX/drawingWindow.getPrefWidth() == valueScaleX);
        assertTrue(resizeDrawingWindowY/drawingWindow.getPrefHeight() == valueScaleY);
    }
    @Test 
    public void testSliderValueThreePointFive(){
        sliderZoom.getSlider().setValue(3.5);
        double resizeDrawingWindowX = drawingWindow.getPrefWidth()*3.5;
        double resizeDrawingWindowY = drawingWindow.getPrefHeight()*3.5;

        drawingWindow.setScaleX(sliderZoom.getSlider().getValue());
        double valueScaleX = drawingWindow.getScaleX();
        drawingWindow.setScaleY(sliderZoom.getSlider().getValue());
        double valueScaleY = drawingWindow.getScaleY();
        
        assertTrue(resizeDrawingWindowX/drawingWindow.getPrefWidth() == valueScaleX);
        assertTrue(resizeDrawingWindowY/drawingWindow.getPrefHeight() == valueScaleY);
    }

    @Test 
    public void testSliderValueFour(){
        sliderZoom.getSlider().setValue(4);
        double resizeDrawingWindowX = drawingWindow.getPrefWidth()*4;
        double resizeDrawingWindowY = drawingWindow.getPrefHeight()*4;

        drawingWindow.setScaleX(sliderZoom.getSlider().getValue());
        double valueScaleX = drawingWindow.getScaleX();
        drawingWindow.setScaleY(sliderZoom.getSlider().getValue());
        double valueScaleY = drawingWindow.getScaleY();
        
        assertTrue(resizeDrawingWindowX/drawingWindow.getPrefWidth() == valueScaleX);
        assertTrue(resizeDrawingWindowY/drawingWindow.getPrefHeight() == valueScaleY);
    }

    @Test 
    public void testSliderValueFourPointFive(){
        sliderZoom.getSlider().setValue(4.5);
        double resizeDrawingWindowX = drawingWindow.getPrefWidth()*4.5;
        double resizeDrawingWindowY = drawingWindow.getPrefHeight()*4.5;

        drawingWindow.setScaleX(sliderZoom.getSlider().getValue());
        double valueScaleX = drawingWindow.getScaleX();
        drawingWindow.setScaleY(sliderZoom.getSlider().getValue());
        double valueScaleY = drawingWindow.getScaleY();
        
        assertTrue(resizeDrawingWindowX/drawingWindow.getPrefWidth() == valueScaleX);
        assertTrue(resizeDrawingWindowY/drawingWindow.getPrefHeight() == valueScaleY);
    }

    @Test
    public void testSliderMaxValue(){
        sliderZoom.getSlider().setValue(5);
        double resizeDrawingWindowX = drawingWindow.getPrefWidth()*5;
        double resizeDrawingWindowY = drawingWindow.getPrefHeight()*5;

        drawingWindow.setScaleX(sliderZoom.getSlider().getValue());
        double valueScaleX = drawingWindow.getScaleX();
        drawingWindow.setScaleY(sliderZoom.getSlider().getValue());
        double valueScaleY = drawingWindow.getScaleY();
        
        assertTrue(resizeDrawingWindowX/drawingWindow.getPrefWidth() == valueScaleX);
        assertTrue(resizeDrawingWindowY/drawingWindow.getPrefHeight() == valueScaleY);
    }
    @Test
    public void testLabelSliderZoom(){
        sliderZoom.setLabelOfSlider(labelSliderZoom);
        sliderZoom.getSlider().setValue(1.5);
        String labelValue = labelSliderZoom.getText();
        String sliderValue = ((int)(sliderZoom.getSlider().getValue()*100) + "%");
        
        assertTrue(labelValue.equals(sliderValue));
    }

    @Test
    public void testLabelSliderZoomMin(){
        sliderZoom.setLabelOfSlider(labelSliderZoom);
        sliderZoom.getSlider().setValue(0.5);
        String labelValue = labelSliderZoom.getText();
        String sliderValue = ((int)(sliderZoom.getSlider().getValue()*100) + "%");
        
        assertTrue(labelValue.equals(sliderValue));
    }
    
    @Test
    public void testLabelSliderZoomDefaultValue(){
        sliderZoom.setLabelOfSlider(labelSliderZoom);
        sliderZoom.getSlider().setValue(1);
        String labelValue = labelSliderZoom.getText();
        String sliderValue = ((int)(sliderZoom.getSlider().getValue()*100) + "%");
        
        assertTrue(labelValue.equals(sliderValue));
    }

    @Test
    public void testLabelSliderZoomValueOnePointFive(){
        sliderZoom.setLabelOfSlider(labelSliderZoom);
        sliderZoom.getSlider().setValue(1.5);
        String labelValue = labelSliderZoom.getText();
        String sliderValue = ((int)(sliderZoom.getSlider().getValue()*100) + "%");
        
        assertTrue(labelValue.equals(sliderValue));
    }
    @Test
    public void testLabelSliderZoomValueTwo(){
        sliderZoom.setLabelOfSlider(labelSliderZoom);
        sliderZoom.getSlider().setValue(2);
        String labelValue = labelSliderZoom.getText();
        String sliderValue = ((int)(sliderZoom.getSlider().getValue()*100) + "%");
        
        assertTrue(labelValue.equals(sliderValue));
    }
    @Test
    public void testLabelSliderZoomValueTwoPointFive(){
        sliderZoom.setLabelOfSlider(labelSliderZoom);
        sliderZoom.getSlider().setValue(2.5);
        String labelValue = labelSliderZoom.getText();
        String sliderValue = ((int)(sliderZoom.getSlider().getValue()*100) + "%");
        
        assertTrue(labelValue.equals(sliderValue));
    }
    @Test
    public void testLabelSliderZoomValueThree(){
        sliderZoom.setLabelOfSlider(labelSliderZoom);
        sliderZoom.getSlider().setValue(3);
        String labelValue = labelSliderZoom.getText();
        String sliderValue = ((int)(sliderZoom.getSlider().getValue()*100) + "%");
        
        assertTrue(labelValue.equals(sliderValue));
    }
    @Test
    public void testLabelSliderZoomValueThreePointFive(){
        sliderZoom.setLabelOfSlider(labelSliderZoom);
        sliderZoom.getSlider().setValue(3.5);
        String labelValue = labelSliderZoom.getText();
        String sliderValue = ((int)(sliderZoom.getSlider().getValue()*100) + "%");
        
        assertTrue(labelValue.equals(sliderValue));
    }
    @Test
    public void testLabelSliderZoomValueFour(){
        sliderZoom.setLabelOfSlider(labelSliderZoom);
        sliderZoom.getSlider().setValue(4.0);
        String labelValue = labelSliderZoom.getText();
        String sliderValue = ((int)(sliderZoom.getSlider().getValue()*100) + "%");
        
        assertTrue(labelValue.equals(sliderValue));
    }
    @Test
    public void testLabelSliderZoomValueFourPointFive(){
        sliderZoom.setLabelOfSlider(labelSliderZoom);
        sliderZoom.getSlider().setValue(4.5);
        String labelValue = labelSliderZoom.getText();
        String sliderValue = ((int)(sliderZoom.getSlider().getValue()*100) + "%");
        
        assertTrue(labelValue.equals(sliderValue));
    }
    @Test
    public void testLabelSliderZoomMax(){
        sliderZoom.setLabelOfSlider(labelSliderZoom);
        sliderZoom.getSlider().setValue(5);
        String labelValue = labelSliderZoom.getText();
        String sliderValue = ((int)(sliderZoom.getSlider().getValue()*100) + "%");
        
        assertTrue(labelValue.equals(sliderValue));
    }

}
