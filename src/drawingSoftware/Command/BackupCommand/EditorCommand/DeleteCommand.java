package drawingSoftware.Command.BackupCommand.EditorCommand;

import drawingSoftware.Editor.Editor;

public class DeleteCommand extends EditorAbstractCommand{


    /*

    * Delete Command is the class which delegates to the Editor the business
    * logic for deleting the selected node. 
    */

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
