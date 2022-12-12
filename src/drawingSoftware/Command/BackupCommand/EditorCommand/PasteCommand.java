package drawingSoftware.Command.BackupCommand.EditorCommand;

import drawingSoftware.Editor.Editor;

/*

 * Paste Command is the class who delegate to the Editor the business
 * logic for pasting the selected node. 
 */

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

