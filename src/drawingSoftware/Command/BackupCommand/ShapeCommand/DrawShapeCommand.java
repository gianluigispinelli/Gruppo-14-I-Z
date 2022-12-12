package drawingSoftware.Command.BackupCommand.ShapeCommand;

import drawingSoftware.Model;
import javafx.scene.shape.Shape;

/*

 * DrawShapeCommand is the Command class which holds the business logic for adding a figure.
 * Before doing that, it saved the current state of the Model. 
 * After that, a new shape is added through the addShape method.
 */

public class DrawShapeCommand extends ShapeCommand{

    public DrawShapeCommand(Model model, Shape shape) {
        super(model, shape);
    }

    @Override
    public boolean execute() {
        saveBackup();
        model.addShape(shape);
        return true; 
    }
}
