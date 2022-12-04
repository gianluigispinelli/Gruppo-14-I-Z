package drawingSoftware;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


public class DrawShapeCommand implements Command{
    private Pane receiverPaneObject;
    private Shape shape;
    private Model model; 
    

    public DrawShapeCommand(Model model, Pane receiverPaneObject, Shape shape) {
        this.model = model; 
        System.out.println("command\n");
        this.receiverPaneObject = receiverPaneObject;
        this.shape  = shape;
    }

    @Override
    public void execute() {
        System.out.println("eseguo\n");
        model.addShape(shape);
    }
    
}
