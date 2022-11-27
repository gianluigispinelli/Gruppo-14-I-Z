package drawingSoftware;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public class SegmentState implements State{

    @Override
    public void drawShape(ColorPicker borderColorPicker,ColorPicker interiorColorPicker, GraphicsContext graphicsContext, double startDragX, double startDragY, double finalDragX, double finalDragY) {
        graphicsContext.setStroke(borderColorPicker.getValue());
        graphicsContext.strokeLine(startDragX, startDragY,finalDragX,finalDragY); 
        
    }
    @Override
    public ObservableBooleanValue isNotSegmentState() {
        ObservableBooleanValue visible = new SimpleBooleanProperty(false);
        return visible;
        
    }
    
}