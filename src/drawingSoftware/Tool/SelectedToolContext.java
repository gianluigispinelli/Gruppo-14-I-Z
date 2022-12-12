package drawingSoftware.Tool;

import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/*
 * 
 * State Pattern Context 
 * 
 */

public class SelectedToolContext{
    
    private Tool currentTool;
    private ColorPicker borderColorPicker;
    private ColorPicker fillColorPicker;
    private TextField widthTextField;
    private TextField heightTextField;
    private Pane drawingWindow;
    private MouseEvent e1;


    private EventHandler<MouseEvent> pressEvent = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {
            if(e.getButton() == MouseButton.PRIMARY){
                e1 = e;
            }
            e.consume();
        }
    };

    private EventHandler<MouseEvent> releasedEvent = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {
            if(e.getButton() == MouseButton.PRIMARY){
                currentTool.setToolParameters(e1, e);
                useSelectedTool();
            }
            e.consume();
        }
    };

    public SelectedToolContext(Tool currentTool,Pane drawingWindow){
        /*
         * @param {State} initial state when I open the program
         * 
         */
        this.currentTool = currentTool;
        this.drawingWindow = drawingWindow;
        this.drawingWindow.addEventHandler(MouseEvent.MOUSE_PRESSED, pressEvent); 
        this.drawingWindow.addEventHandler(MouseEvent.MOUSE_RELEASED, releasedEvent);
    }
    
    public void changeTool(Tool currentTool){
        //this.drawingWindow.removeEventHandler(MouseEvent.MOUSE_DRAGGED, dragEvent);
        drawingWindow.setOnMouseClicked(null);
        drawingWindow.setOnMouseMoved(null);
        this.currentTool = currentTool;  
    }

    public void useSelectedTool(){
        //this.drawingWindow.setOnMouseMoved(null);
        //this.drawingWindow.setOnMouseClicked(null);
        currentTool.useSelectedTool();

    }

    public Tool getSelectedTool(){
        /*
         * @return {s} which shape button is pressed.
         */
        return currentTool;
    }
    
    public ObservableBooleanValue isLineTool(){
        return currentTool.isNotLineTool();  
    }
}