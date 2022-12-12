package drawingSoftware.Command.BackupCommand.ShapeCommand;

import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.BackupCommand;
import drawingSoftware.Managers.MoveManager;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseButton;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/*

 * MoveCommand is the Command class which holds the business logic for moving a figure.
 * Before doing that, it saved the current state of the Model. 
 * This class delegated the logic to a MoveManager istance whose method, movelogic, relocates
 * the selected shape according to the user cursor position.  
 */

public class MoveCommand extends BackupCommand {
    private Pane drawingWindow;
    private Model model; 
    private MoveManager moveManager;    /* the receiver */

    public MoveCommand(Pane drawingWindow,Model model, MoveManager moveManager) {
        this.drawingWindow = drawingWindow;
        this.model = model;
        this.moveManager = moveManager;        
    }

    @Override
    public boolean execute() {
        saveBackup();
        moveManager.moveLogic(drawingWindow, model); 
        return true;
    }

    public void saveBackup(){
        super.backup = FXCollections.observableArrayList(model.getAllShapes());   /* the backup is how the shapes were before exec. the command */
    }

    public void undo(){
        model.addAll(super.backup);
    }

    public Model getModel() {
        return model;
    }
}
