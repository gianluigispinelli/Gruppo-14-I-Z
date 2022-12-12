package drawingSoftware.Testing;

import drawingSoftware.SliderZoom;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;


/*
 * SliderZoomTestC main is to check if scale of drawable window corresponding to scale of slider value
 */

public class SliderZoomTest {
    
      
    SliderZoom sliderZoom;
    Slider slider;
    Pane drawingWindow;
    Label labelSliderZoom;
    double valueSlider;
    double valueDrawing;
    
    public SliderZoomTest(){
        final JFXPanel fxPanel = new JFXPanel();   
        slider = new Slider();
        sliderZoom = new SliderZoom(slider);
        drawingWindow = new Pane();
        drawingWindow.setPrefWidth(959);
        drawingWindow.setPrefHeight(526);
        labelSliderZoom = new Label();
        
    }

    public double valueSliderDrawingX(double slider){
        double resizeDrawingWindowX = drawingWindow.getPrefWidth()*slider;
        drawingWindow.setScaleX(sliderZoom.getSlider().getValue());
        return resizeDrawingWindowX/drawingWindow.getPrefWidth();
    
    }
    public double valueSliderDrawingY(double slider){
        double resizeDrawingWindowY = drawingWindow.getPrefHeight()*slider;
        drawingWindow.setScaleY(sliderZoom.getSlider().getValue());
        return resizeDrawingWindowY/drawingWindow.getPrefHeight();
       
    }
}
