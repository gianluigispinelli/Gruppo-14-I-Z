package drawingSoftware.Command.BackupCommand.EditorCommand;

import drawingSoftware.Editor.Editor;

public class CopyCommand extends EditorAbstractCommand{

    public CopyCommand(Editor editor) {
        super(editor);
    }

    public boolean execute(){
        editor.copy();
        return false; 
    }
}
