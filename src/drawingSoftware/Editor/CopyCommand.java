package drawingSoftware.Editor;

import drawingSoftware.Controller;

public class CopyCommand extends EditorAbstractCommand{

    public CopyCommand(Controller controller, Editor editor) {
        super(controller, editor);
    }

    public boolean execute(){
        // app.clipboard ? 
        editor.copy();
        return false; 
    }
}
