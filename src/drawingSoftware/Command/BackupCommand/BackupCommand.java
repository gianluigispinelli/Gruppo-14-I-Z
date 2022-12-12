package drawingSoftware.Command.BackupCommand;

import drawingSoftware.Command.Command;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/*

 * BackupCommand is the abstract class for those Commands who need to save their backup. 
 * The backup is a "snapshot" of the Model BEFORE the command is being executed.
 * Once their undo() method is called, the commands set the current state of the Model with the
 * one they have saved on backup.
 * The backup is saved as an ObservableList<Node>, which are the current nodes the model has. 
 * 
 */

public abstract class BackupCommand implements Command{

    protected ObservableList<Node> backup;

    public ObservableList<Node> getBackup() {
        return backup;
    }

    public void setBackup(ObservableList<Node> backup) {
        this.backup = backup;
    }

    public void saveBackup(){}

    public void undo(){}

    @Override
    public abstract boolean execute();
    
}
