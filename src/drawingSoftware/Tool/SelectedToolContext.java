package drawingSoftware.Tool;


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

public class SelectedToolContext{
    
    private Tool currentTool;
    private ColorPicker borderColorPicker;
    private ColorPicker fillColorPicker;

    
    public SelectedToolContext(Tool currentTool, Pane drawingWindow, ColorPicker borderColorPicker, ColorPicker fillColorPicker){
        /*
         * @param {State} initial state when I open the program
         * 
         */
        this.currentTool = currentTool;
        this.borderColorPicker = borderColorPicker;
        this.fillColorPicker = fillColorPicker;    
    }
    
    public void changeTool(Tool currentTool){
        this.currentTool = currentTool;
        currentTool.useSelectedTool(borderColorPicker, fillColorPicker);
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


    public Tool getSelectedTool(){
        /*
         * @return {s} which shape button is pressed.
         */
        return currentTool;
    }
    
    public ObservableBooleanValue isLineTool(){
        /*
         * 
         */
        return currentTool.isNotLineTool();  
    }
}