package drawingSoftware.Command.BackupCommand.ShapeCommand;

import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.BackupCommand;
import javafx.collections.FXCollections;
import javafx.scene.shape.Shape;

/*

 * ShapeCommand is the abstract class for those commands whose logic is working
 * directly with the model methods. The commands who inherits from
 * this class, since it extends the BackupCommand
 * abstract class, save the status of the model before executing their specific operation. 
 */

public abstract class ShapeCommand extends BackupCommand{

   protected Model model;
   protected Shape shape;

   /*

   * Mandatory constructor for all its sublclasses 
   */
   
   public ShapeCommand(Model model, Shape shape){
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

   public Model getModel() {
   return model;
}

}
