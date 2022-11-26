package hellofx;

import java.beans.EventHandler;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class EllipseState implements State{

    @Override
    public void drawShape(ColorPicker borderColorPicker, ColorPicker interiorColorPicker, GraphicsContext graphicsContext, double startDragX, double startDragY, double finalDragX, double finalDragY) {
        
        graphicsContext.setStroke(borderColorPicker.getValue());
        graphicsContext.setFill(interiorColorPicker.getValue());
        graphicsContext.strokeOval(Math.min(startDragX, finalDragX),Math.min(startDragY, finalDragY),Math.abs(finalDragX-startDragX),Math.abs(finalDragY-startDragY)); 
        graphicsContext.fillOval(Math.min(startDragX, finalDragX),Math.min(startDragY, finalDragY),Math.abs(finalDragX-startDragX),Math.abs(finalDragY-startDragY));    
    }
    
}
