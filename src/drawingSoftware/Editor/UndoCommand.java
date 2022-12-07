package drawingSoftware.Editor;

import drawingSoftware.Controller;
/*

 * The caretaker of the Editor's snapshot 
 */
public class UndoCommand extends BackupCommand{

    private Controller controller; 

    public UndoCommand(Controller controller) {
        this.controller = controller;
    }

    public boolean execute(){
        controller.undoOperation();
        return false; 
    }
}
