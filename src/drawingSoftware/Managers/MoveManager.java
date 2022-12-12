package drawingSoftware.Managers;

import drawingSoftware.Model;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/*

 * receiver of the moveCommand: it executes the business logic related to the movement of a shape 
 * 
 */

public class MoveManager {

    private double currentX;
    private double currentY;

    public MoveManager(){}

    public void moveLogic(Pane drawingWindow, Model model){
        drawingWindow.setOnMouseDragged(e->{
            Shape selectedShape = (Shape) model.getSelectedShape();
            if(e.getButton() == MouseButton.PRIMARY){
                this.currentX = e.getX();
                this.currentY =  e.getY();
                if (selectedShape != null){
                    selectedShape.relocate(currentX - selectedShape.getLayoutBounds().getWidth()/2, currentY - selectedShape.getLayoutBounds().getHeight()/2);   
                }  
            }
            e.consume();
        }); 
    }
    
}
