package drawingSoftware;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class SelectTool implements Tool{
    private Pane drawingWindow;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private Node shapeToSelect;
    private MouseEvent pressed;
    private MouseEvent released;
 
    public SelectTool(Pane drawingWindow) {
        this.drawingWindow = drawingWindow;
        //this.captureMouseEvent();
        
        
    }
    


    

    


    public ObservableBooleanValue isNotLineTool() {
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }

    @Override
    public void useSelectedTool(ColorPicker borderColor, ColorPicker fillColor) {
        Node selectedShape = drawingWindow.lookup("#selected");

        if(selectedShape != null){
            selectedShape.setId("");
        }
        drawingWindow.setOnMousePressed(e ->{
        
            if(e.getButton() == MouseButton.PRIMARY){
                this.pressed = e;
                this.setStartX(e.getX());
                this.setStartY(e.getY());
                this.shapeToSelect = (Shape)pressed.getTarget();
                shapeToSelect.setId("selected");
            }
        //e.consume();
        });
    
        drawingWindow.setOnMouseReleased(e ->{
            if(e.getButton() == MouseButton.PRIMARY){
                this.released = e;
                this.setEndX(e.getX());
                this.setEndY(e.getY());
                this.shapeToSelect.setTranslateX(shapeToSelect.getTranslateX() + released.getX() - startX);
                this.shapeToSelect.setTranslateY(shapeToSelect.getTranslateY()+ released.getY() - startY );
                //e.consume();                
            }
            
        });



        
    }


    public void setStartX(double startX) {
        this.startX = startX;
    }


    public void setStartY(double startY) {
        this.startY = startY;
    }


    public void setEndX(double endX) {
        this.endX = endX;
    }


    public void setEndY(double endY) {
        this.endY = endY;
    }

    public void setShapeToSelect(Node shapeToSelect) {
        this.shapeToSelect = shapeToSelect;
    }
    
}
