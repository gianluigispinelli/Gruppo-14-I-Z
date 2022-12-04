package drawingSoftware.State;
import java.util.ArrayList;

import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.Node;
import javafx.scene.Parent;
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

    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        addAllDescendents(root, nodes);
        return nodes;
    }
    
    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent)
                addAllDescendents((Parent)node, nodes);
        }
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