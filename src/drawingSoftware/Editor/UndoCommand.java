package drawingSoftware.Editor;

import drawingSoftware.Model;
import drawingSoftware.Load.Save.Command;

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
        model.removeLast();
    }     
}
