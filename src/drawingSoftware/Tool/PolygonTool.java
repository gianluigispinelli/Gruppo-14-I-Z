package drawingSoftware.Tool;


import drawingSoftware.Controller;
import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.ShapeCommand.DrawShapeCommand;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/*Concrete class for drawing not regular Polygon */

public class PolygonTool implements Tool{
    private Controller controller; 
    private Model model;
    private Pane drawingWindow;
    private ColorPicker borderColorPicker;
    private ColorPicker fillColorPicker;
    private Polygon currentPolygon;

    // Constructor
    public PolygonTool(Controller controller, Model model, Pane drawingWindow, ColorPicker borderColorPicker, ColorPicker fillColorPicker) {
        this.controller = controller; 
        this.model = model;
        this.drawingWindow = drawingWindow;
        this.borderColorPicker = borderColorPicker;
        this.fillColorPicker = fillColorPicker;        
        this.deselectAllShape();
    }

    private void deselectAllShape(){
        Shape deselectShape = (Shape)model.getSelectedShape();
        if(deselectShape != null){
            deselectShape.strokeProperty().unbind();
            deselectShape.fillProperty().unbind();
        }
    model.setCurrentShape(null);
    }

     /* Unlike the concrete class that extends ShapeTool bastract class, the PolygonTool class implements tool interface because
     * it need  to capture events by itself. If we provided the events as parameters to this class, these events conflicts with the 
     * other events. So the method useSelectedTool capture the clicked and moved mouse events and dynamically draw the Polygon by instantiate a
     * DrawShapeCommand. 
     */
    @Override
    public void useSelectedTool() {
        drawingWindow.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (currentPolygon == null) {
                    currentPolygon = new Polygon();
                    currentPolygon.getPoints().addAll(e.getX(), e.getY());
                    currentPolygon.setStroke(borderColorPicker.getValue());
                    currentPolygon.setFill(fillColorPicker.getValue());
                    DrawShapeCommand draw = new DrawShapeCommand(model, currentPolygon);
                    controller.executeCommand(draw);
                }
                currentPolygon.getPoints().addAll(e.getX(), e.getY());
            } else {
                currentPolygon = null ;
            }
            e.consume();
        });
        drawingWindow.setOnMouseMoved(e -> {
            if (currentPolygon != null) {
                currentPolygon.getPoints().set(currentPolygon.getPoints().size()-2, e.getX());
                currentPolygon.getPoints().set(currentPolygon.getPoints().size()-1, e.getY());
            }
            e.consume();
        });

    }

    @Override
    public ObservableBooleanValue isNotLineTool() {
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }

    /*
     * Unused method because the events are captured in the useSelectedToolMethod. It Needs Refactor --> Adjust Design.
     */
    @Override
    public void setToolParameters(MouseEvent e1, MouseEvent e2) {
        
        
    }
    
}
