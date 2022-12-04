package drawingSoftware.Editor;

import drawingSoftware.Load.Save.Command;

public class PasteCommand implements Command{

    Editor editor; 

    public PasteCommand(Editor editor){
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.paste();
    }
}

