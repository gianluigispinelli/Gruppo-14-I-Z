package drawingSoftware.Tool;

import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.input.MouseEvent;

/* Interface implemented by all the tool that acts as the State interface of the State pattern.
 * 
 */

import javafx.scene.input.MouseEvent;

/* Interface implemented by all the tool that acts as the State interface of the State pattern.
 * 
 */

public interface Tool {
    public void useSelectedTool();
    
    //Method to check that the selected tool is not the line tool. 
    //This because when the line tool is selected the GUI mustn't show to the user the fillColorPicker.
    public ObservableBooleanValue isNotLineTool();

    /*This method is used to pass to the selected tool the mouse events captured and that are needed to select, draw and move a shape */
    public void setToolParameters(MouseEvent e1, MouseEvent e2);
    
}
