package drawingSoftware.Tool;

import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public interface Tool {
    public void useSelectedTool(ColorPicker borderColor, ColorPicker fillColor);
    
    public ObservableBooleanValue isNotLineTool();
    
}
