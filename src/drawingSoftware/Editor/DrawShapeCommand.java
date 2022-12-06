package drawingSoftware.Editor;

import drawingSoftware.Controller;
import drawingSoftware.Model;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


public class DrawShapeCommand extends EditorAbstractCommand{
    private Pane receiverPaneObject;
    private Shape shape;
    private Model model; 

    public DrawShapeCommand(Controller controller, Editor editor) {
        super(controller, editor);
    }

    /*  */
    public DrawShapeCommand(Model model, Pane receiverPaneObject, Shape shape) {
        super(null, null);
        this.model = model; 
        this.receiverPaneObject = receiverPaneObject;
        this.shape = shape; 
    }

    @Override
    public boolean execute() {
        // saveBackup();
        model.addShape(shape);
        return true; 
    }
    
}
