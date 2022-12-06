package drawingSoftware.Editor;

import drawingSoftware.Controller;
/*

 * The caretaker of the Editor's snapshot 
 */
public class UndoCommand extends EditorAbstractCommand{

    public UndoCommand(Controller controller, Editor editor) {
        super(controller, editor);
    }

    public boolean execute(){
        controller.undoOperation();
        return false; 
    }
}
