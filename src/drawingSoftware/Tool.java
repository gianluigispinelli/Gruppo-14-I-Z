package drawingSoftware;

import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;

public interface Tool {
    public void useSelectedTool(ColorPicker borderColor, ColorPicker fillColor);
    
    public ObservableBooleanValue isNotLineTool();
    
}
