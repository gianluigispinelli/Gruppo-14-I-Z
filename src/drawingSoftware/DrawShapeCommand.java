package drawingSoftware;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


public class DrawShapeCommand implements Command{
    private Pane receiverPaneObject;
    private Shape shape;
    

    public DrawShapeCommand(Pane receiverPaneObject, Shape shape) {
        System.out.println("command\n");
        this.receiverPaneObject = receiverPaneObject;
        this.shape  = shape;
    }


    @Override
    public void execute() {
        System.out.println("eseguo\n");
        this.receiverPaneObject.getChildren().add(this.shape);
        
    }
    
}
