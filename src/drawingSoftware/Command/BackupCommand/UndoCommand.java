package drawingSoftware.Command.BackupCommand;

import drawingSoftware.Controller;

/*

 * UndoCommand is the delegator for the undoOperation, which is executed by the Controller.
 * The latter plays the role of the receiver.
 * When the undoOperation() method is executed, a new command is popped from the stack of
 * the executed commands, and its backup is restored, for those class which inherits from the EditorAbstractCommand
 * and ShapeCommand abstract classes. 
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

    public Controller getController() {
        return controller;
    }
}
