package drawingSoftware.Command.BackupCommand.EditorCommand;

import drawingSoftware.Editor.Editor;

public class CutCommand extends EditorAbstractCommand{

    public CutCommand(Editor editor) {
        super(editor);
    }
    
    @Override
    public boolean execute() {
        saveBackup();   
        editor.cut();
        return true; 
    }
}
