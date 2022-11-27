package drawingSoftware;

import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
/*
 * State Pattern Interface to selected which shapes should be drawed in the canvas
 */
public interface State{
    //TODO: Valutare se serve aggiungere istanza di Context (SelectedFigure) come nel pattern
    public void drawShape(ColorPicker borderColorPicker, ColorPicker interiorColorPicker, GraphicsContext graphicsContext, double startDragX, double startDragY, double finalDragX, double finalDragY);
    public ObservableBooleanValue isNotSegmentState();
}