package drawingSoftware.Editor;

import drawingSoftware.Controller;

public class PasteCommand extends EditorAbstractCommand{

    public PasteCommand(Controller controller, Editor editor) {
        super(controller, editor);
    }    

    @Override
    public boolean execute() {
        saveBackup();
        editor.paste();
        return true; 
    }
}

