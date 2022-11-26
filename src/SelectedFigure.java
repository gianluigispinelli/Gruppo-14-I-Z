package hellofx;

import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public class SelectedFigure{   // E' il Context
    
    private State s;

    public SelectedFigure(State initialState){
        this.s = initialState;
    }

    public void changeState(State s){
        this.s = s;
    }

    public void drawShape(ColorPicker borderColorPicker, ColorPicker inteColorPicker, GraphicsContext graphicsContext, double startDragX, double startDragY, double finalDragX, double finalDragY){
        s.drawShape(borderColorPicker, inteColorPicker, graphicsContext, startDragX, startDragY, finalDragX, finalDragY);
    }

    public State getState(){
        return s;
    }

}