package drawingSoftware;



import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;

public class SelectState implements State {

    @Override
    public void drawShape(Pane drawableWindow, ColorPicker borderColorPicker, ColorPicker interiorColorPicker,
            double startDragX, double startDragY, double finalDragX, double finalDragY) {
                Node removeBorder = (Node) drawableWindow.lookup("#selected");
                Node changeId = (Node) drawableWindow.lookup("#selectedShape");
                if (changeId!=null)
                changeId.setId("");
                drawableWindow.getChildren().remove(removeBorder);
        
    }

    @Override
    public ObservableBooleanValue isNotSegmentState() {
        // TODO Auto-generated method stub
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }
    
}
