package drawingSoftware.Tool;


import drawingSoftware.Controller;
import drawingSoftware.Model;
import drawingSoftware.Editor.MoveCommand;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class SelectTool implements Tool{
    private Controller controller; 
    private Model model; 
    private Pane drawingWindow;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private Node shapeToSelect;
    private MouseEvent pressed;
    private MouseEvent released;

    private Shape selectedShape; 
 
    public SelectTool(Controller controller, Model model, Pane drawingWindow) {
        this.controller = controller;
        this.model = model; 
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

                if (e.getTarget() instanceof Pane){
                    ObservableList<Node> all = drawingWindow.getChildren();
                    for (Node node : all) {
                        Shape shape = (Shape) node; 
                        shape.getStrokeDashArray().removeAll(25d,20d,5d,20d);
                        shape.setStrokeWidth(1);
                        if (shapeToSelect != null){
                            shapeToSelect.setId("");
                        }
                    }
                }
                else{
                    if (this.shapeToSelect != null){
                        Shape modify = (Shape)this.shapeToSelect; 
                        modify.setId("");
                        this.selectedShape.setStrokeWidth(1);
                        this.selectedShape.getStrokeDashArray().removeAll(25d,20d,5d,20d);
                    }
                    this.pressed = e;
                    this.setStartX(e.getX());
                    this.setStartY(e.getY());
                    this.shapeToSelect = (Shape)pressed.getTarget();
                    shapeToSelect.setId("selected");
                    this.selectedShape = (Shape)shapeToSelect;
                    this.selectedShape.getStrokeDashArray().addAll(25d,20d,5d,20d);
                    this.selectedShape.setStrokeWidth(5);
                    this.selectedShape.toFront();                
                }
            }
        //e.consume();
        });
    
        drawingWindow.setOnMouseReleased(e ->{

            if (!(e.getTarget() instanceof Pane)){
                if(e.getButton() == MouseButton.PRIMARY){
                    this.released = e;
                    this.setEndX(e.getX());
                    this.setEndY(e.getY());
                    MoveCommand moveCommand =  new MoveCommand(model, (Shape)this.shapeToSelect, startX, startY, endX, endY);
                    controller.executeCommand(moveCommand);
                    //e.consume();                
                }
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
