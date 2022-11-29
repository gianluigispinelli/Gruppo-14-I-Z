package drawingSoftware;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;

/*
 * 
 * State Pattern Context 
 * 
 */

public class SelectedFigure{ 
    
    private State s;

    
    public SelectedFigure(State initialState){
        /*
         * @param {State} initial state when I open the program
         * 
         */
        this.s = initialState;
    }

    public void changeState(State s){
        this.s = s;
    }

    /*
    * @param {borderColorPicker} border color of shape
    * @param {inteColorPicker} interior color of shape
    * @param {graphicsContext} graphic context of the canvas
    * @param {startDragX, startDragY} initial coordinates of left-click in the canvas
    * @param {finalDragX, finalDragY} final coordinates of released left-click in the canvas
    */
    public void drawShape(Pane drawableWindow, ColorPicker borderColorPicker, ColorPicker inteColorPicker, double startDragX, double startDragY, double finalDragX, double finalDragY){
        
         s.drawShape(drawableWindow, borderColorPicker, inteColorPicker, startDragX, startDragY, finalDragX, finalDragY);
    }

    public State getState(){
        /*
         * @return {s} which shape button is pressed.
         */
        return s;
    }
    
    public ObservableBooleanValue getSegmentState(){
        /*
         * 
         */
        return s.isNotSegmentState();  
    }
}