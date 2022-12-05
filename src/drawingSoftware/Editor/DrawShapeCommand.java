package drawingSoftware.Editor;

import drawingSoftware.Model;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


public class DrawShapeCommand implements Command{
    private Pane receiverPaneObject;
    private Shape shape;
    private Model model; 
    

    public DrawShapeCommand(Model model, Pane receiverPaneObject, Shape shape) {
        this.model = model; 
        this.receiverPaneObject = receiverPaneObject;
        this.shape  = shape;
    }

    @Override
    public void execute() {
        model.addShape(shape);
    }
    
}
