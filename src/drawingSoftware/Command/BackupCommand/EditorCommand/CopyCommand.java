package drawingSoftware.Command.BackupCommand.EditorCommand;

import drawingSoftware.Editor.Editor;

public class CopyCommand extends EditorAbstractCommand{

    /*

    * Copy Command is the class who delegate to the Editor the business
    * logic for copying the selected node. 
    */

    public CopyCommand(Editor editor) {
        super(editor);
    }

    public boolean execute(){
        editor.copy();
        return false; 
    }
}
