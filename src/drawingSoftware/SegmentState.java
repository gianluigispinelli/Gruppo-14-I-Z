package drawingSoftware;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class SegmentState implements State{

    @Override
    public void drawShape(Pane drawingWindow, ColorPicker borderColorPicker,ColorPicker interiorColorPicker,double startDragX, double startDragY, double finalDragX, double finalDragY) {

        finalDragX = finalDragX < 0.0 ? 0.0 : finalDragX;
        startDragX = startDragX < 0.0 ? 0.0 : startDragX;
        finalDragY = finalDragY < 0.0 ? 0.0 : finalDragY;
        startDragY = startDragY < 0.0 ? 0.0 : startDragY;

        Line line = new Line();
        
        line.setStartX(startDragX);
        line.setEndX(finalDragX);

        line.setStartY(startDragY);
        line.setEndY(finalDragY);

        line.setStroke(borderColorPicker.getValue());   
        
        drawingWindow.getChildren().addAll(line);
    }
    @Override
    public ObservableBooleanValue isNotSegmentState() {
        ObservableBooleanValue visible = new SimpleBooleanProperty(false);
        return visible;
        
    }
    
}