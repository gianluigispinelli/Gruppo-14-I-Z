package drawingSoftware.Editor;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class BackupCommand implements Command{

    protected ObservableList<Node> backup; 


    public void saveBackup(){}

    public void undo(){}

    @Override
    public boolean execute() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
