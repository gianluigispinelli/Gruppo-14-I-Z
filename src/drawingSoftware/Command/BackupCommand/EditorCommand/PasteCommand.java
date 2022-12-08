package drawingSoftware.Command.BackupCommand.EditorCommand;

import drawingSoftware.Editor.Editor;

public class PasteCommand extends EditorAbstractCommand{

    public PasteCommand(Editor editor) {
        super(editor);
    }    

    @Override
    public boolean execute() {
        saveBackup();
        editor.paste();
        return true; 
    }
}

