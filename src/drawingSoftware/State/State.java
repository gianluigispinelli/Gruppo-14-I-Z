package drawingSoftware.State;

import drawingSoftware.Model;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
/*
 * State Pattern Interface to selected which shapes should be drawed in the canvas
 */
public interface State{
    //TODO: Valutare se serve aggiungere istanza di Context (SelectedFigure) come nel pattern
    public void drawShape(Model model, Pane drawableWindow, ColorPicker borderColorPicker, ColorPicker interiorColorPicker,double startDragX, double startDragY, double finalDragX, double finalDragY);

    public void useTool();
    
    public ObservableBooleanValue isNotSegmentState();
}