package drawingSoftware.Tool;


import drawingSoftware.Controller;
import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.ShapeCommand.MoveCommand;
import drawingSoftware.Managers.MoveManager;
import drawingSoftware.Shapes.MyBoundingBox;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.EventTarget;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class SelectTool implements Tool{
    private Pane drawingWindow;
    private Model model; 
    private EventTarget eventTarget;
    private MouseButton buttonEvent1;
    private ColorPicker borderColorPicker;
    private ColorPicker fillColorPicker;
    private Controller controller;
    private MoveCommand moveCommand;

 
    // Constructor
    public SelectTool(Controller controller, Model model, Pane drawingWindow, ColorPicker borderColorPicker, ColorPicker  fillColorPicker) {
        this.controller = controller; 
        this.drawingWindow = drawingWindow;
        this.borderColorPicker=borderColorPicker;
        this.fillColorPicker=fillColorPicker;
        this.model = model;
        drawingWindow.setCursor(Cursor.HAND);
        this.moveCommand = new MoveCommand(drawingWindow, model, new MoveManager());
    }
    

    public ObservableBooleanValue isNotLineTool() {
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }

    @Override
    public void useSelectedTool() {
            if(buttonEvent1 == MouseButton.PRIMARY){
                // if I click on the drawing window then if a shape is selected, I deselect it.
                if (eventTarget instanceof Pane){     /* se tocco la drawingWindow */
                    Shape shape = (Shape)model.getSelectedShape();
                    if (shape != null){
                        // When i deselect, the fillColor property and the stroke property have to be unbinded.
                        shape.fillProperty().unbind();
                        shape.strokeProperty().unbind();
                        model.setCurrentShape(null);
                    }
                }
                if (!(eventTarget instanceof MyBoundingBox) && !(eventTarget instanceof Pane)){
                    // When i click on a shape, then the previous selected shape have to be deselected.
                    Shape oldSelected = (Shape)model.getSelectedShape();
                    if (oldSelected != null){
                        oldSelected.strokeProperty().unbind();
                        oldSelected.fillProperty().unbind();
                    }
                    
                    // Set the new selected shape
                    model.setCurrentShape((Node)eventTarget); 
                    /* set the figure as selected in the Model */
                    Shape newSelected = (Shape)model.getSelectedShape();
                     // set the value of the color picker to the color of the selected shape.
                    borderColorPicker.setValue((Color)(newSelected.getStroke()));
                    fillColorPicker.setValue((Color)(newSelected.getFill()));
                    makeSelected(newSelected);
                    // Call to the move command in order to make possible the movement of the selected shape.
                    controller.executeCommand(moveCommand);                    
                }  
                if (eventTarget instanceof MyBoundingBox){
                    // If i click on a selected shape then i deselect it.
                    Shape shape =  (Shape)model.getSelectedShape();
                    shape.fillProperty().unbind();
                    shape.strokeProperty().unbind();
                    model.setCurrentShape(null);
                }   
            }
    }

     //The method set the button clicked and the target of the mouse event
     // by taking in input the event captured in the selectedToolContext.
    @Override
    public void setToolParameters(MouseEvent e1, MouseEvent e2) {

        if(e1.getButton() == MouseButton.PRIMARY){
            this.buttonEvent1 = e1.getButton();
            this.eventTarget = e1.getTarget();
        }
    }

    /*
     * The method take in input the shape that has been clicked, put it to the front and bind the border color and the fill color 
     * to the value of the respective colorPicker.
     */
    private void makeSelected(Shape shape){

        shape.toFront();
        shape.fillProperty().bind(fillColorPicker.valueProperty());
        shape.strokeProperty().bind(borderColorPicker.valueProperty());

    }
}
