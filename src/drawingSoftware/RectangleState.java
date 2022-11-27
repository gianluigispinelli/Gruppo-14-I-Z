package drawingSoftware;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public class RectangleState implements State{

    @Override
    public void drawShape(ColorPicker borderColorPicker, ColorPicker interiorColorPicker, GraphicsContext graphicsContext, double startDragX, double startDragY, double finalDragX, double finalDragY) {
        
        graphicsContext.setStroke(borderColorPicker.getValue());
        graphicsContext.setFill(interiorColorPicker.getValue());
        graphicsContext.strokeRect(Math.min(startDragX, finalDragX),Math.min(startDragY, finalDragY),Math.abs(finalDragX-startDragX),Math.abs(finalDragY-startDragY)); 
        graphicsContext.fillRect(Math.min(startDragX, finalDragX),Math.min(startDragY, finalDragY),Math.abs(finalDragX-startDragX),Math.abs(finalDragY-startDragY));
    }
    @Override
    public ObservableBooleanValue isNotSegmentState() {
        // TODO Auto-generated method stub
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }
    
}
