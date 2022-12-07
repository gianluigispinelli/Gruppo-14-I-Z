package drawingSoftware.Editor;

import drawingSoftware.Model;
import javafx.collections.FXCollections;
import javafx.scene.shape.Shape;

public abstract class ShapeCommand extends BackupCommand{

    protected Model model; 
    protected Shape shape;

    /*

     * Mandatory constructor for all its sublclasses 
     */
     public ShapeCommand(Model model, Shape shape){ /* Model is the receiver of the command */
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
}
