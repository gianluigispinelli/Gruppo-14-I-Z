package drawingSoftware.State;

import drawingSoftware.Model;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;

public class SelectState implements State {

    /*
     * non disegna, seleziona
     */
    @Override
    public void drawShape(Model model, Pane drawableWindow, ColorPicker borderColorPicker, ColorPicker interiorColorPicker,
            double startDragX, double startDragY, double finalDragX, double finalDragY) {
                Node removeBorder = (Node) drawableWindow.lookup("#selected");
                drawableWindow.getChildren().remove(removeBorder);
    }

    @Override
    public ObservableBooleanValue isNotSegmentState() {
        // TODO Auto-generated method stub
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }

    @Override
    public void useTool() {
        // TODO Auto-generated method stub
        
    }
}
