package hellofx;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class RectangleState implements State{

    @Override
    public void drawShape(ColorPicker borderColorPicker, ColorPicker interiorColorPicker, GraphicsContext graphicsContext, double startDragX, double startDragY, double finalDragX, double finalDragY) {
        //interiorColorPicker.setVisible(true);
        graphicsContext.setStroke(borderColorPicker.getValue());
        graphicsContext.setFill(interiorColorPicker.getValue());
        graphicsContext.strokeRect(Math.min(startDragX, finalDragX),Math.min(startDragY, finalDragY),Math.abs(finalDragX-startDragX),Math.abs(finalDragY-startDragY)); 
        graphicsContext.fillRect(Math.min(startDragX, finalDragX),Math.min(startDragY, finalDragY),Math.abs(finalDragX-startDragX),Math.abs(finalDragY-startDragY));
    }
    
}
