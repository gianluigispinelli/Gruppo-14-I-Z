package drawingSoftware.Command.BackupCommand.ShapeCommand;

import drawingSoftware.Model;
import javafx.scene.shape.Shape;


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
