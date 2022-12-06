package drawingSoftware.Editor;

import drawingSoftware.Model;
import javafx.collections.FXCollections;
import javafx.scene.shape.Shape;


public class DrawShapeCommand extends BackupCommand{
    private Shape shape;
    private Model model; 

    public DrawShapeCommand(Model model, Shape shape) {
        this.model = model; 
        this.shape = shape; 
    }

    @Override
    public void saveBackup(){
        super.backup = FXCollections.observableArrayList(model.getAllShapes());   /* the backup is how the shapes were before exec. the command */
    }

    @Override
    public void undo(){
        model.addAll(super.backup);
    }

    @Override
    public boolean execute() {
        saveBackup();
        model.addShape(shape);
        return true; 
    }
}
