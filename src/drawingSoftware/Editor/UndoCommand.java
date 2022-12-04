package drawingSoftware.Editor;


import java.util.Stack;

import drawingSoftware.Model;
import drawingSoftware.Load.Save.Command;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/*

 * The caretaker of the Editor's snapshot 
 */
public class UndoCommand implements Command{

    private Model model; 


    public UndoCommand(Model model){
        this.model = model; 
    }

    @Override
    public void execute() {         /* undo method */
        this.model.backup();    /* backup to a previuos state of the drawingWindow */
    }     
}
