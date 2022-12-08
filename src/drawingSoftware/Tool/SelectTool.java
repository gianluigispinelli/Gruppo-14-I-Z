package drawingSoftware.Tool;


import drawingSoftware.Controller;
import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.ShapeCommand.MoveCommand;
import drawingSoftware.Shapes.MyBoundingBox;
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
    private MouseEvent released;
    private MouseEvent pressed;
 
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

        drawingWindow.setOnMousePressed(e ->{

            if(e.getButton() == MouseButton.PRIMARY){
                if (e.getTarget() instanceof Pane){     /* se tocco la drawingWindow */
                    model.setCurrentShape(null);
                }
                else{       /* non devo selezionare oggetti di tipo MyBoundingBox */
                    if (!(e.getTarget() instanceof MyBoundingBox))  /* se non sto toccando una bounding box */
                        model.setCurrentShape((Node)e.getTarget());  /* setto la figura come corrente nel Model */
                }
            }
        });
    
        drawingWindow.setOnMouseReleased(e ->{
        
            // if (!(e.getTarget() instanceof Pane)){
            //     if(e.getButton() == MouseButton.PRIMARY){
            //         this.released = e;
            //         this.setEndX(e.getX());
            //         this.setEndY(e.getY());
            //         MoveCommand moveCommand =  new MoveCommand(model, (Shape)model.getSelectedShape(), startX, startY, endX, endY);
            //         MoveCommand moveCommand2 =  new MoveCommand(model, controller.getBoundingBox(), startX, startY, endX, endY);
            //         controller.executeCommand(moveCommand);
            //         controller.executeCommand(moveCommand2);
            //     }
            // }
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
