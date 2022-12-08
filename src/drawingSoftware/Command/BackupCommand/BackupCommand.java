package drawingSoftware.Command.BackupCommand;

import drawingSoftware.Command.Command;
import javafx.collections.ObservableList;
import javafx.scene.Node;

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
