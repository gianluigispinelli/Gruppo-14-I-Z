package drawingSoftware.Command.BackupCommand.EditorCommand;

import drawingSoftware.Editor.Editor;

public class DeleteCommand extends EditorAbstractCommand{

    public DeleteCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        saveBackup();
        editor.delete();
        return true; 
    }
}
