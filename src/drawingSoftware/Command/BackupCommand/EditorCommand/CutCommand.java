package drawingSoftware.Command.BackupCommand.EditorCommand;

import drawingSoftware.Editor.Editor;

public class CutCommand extends EditorAbstractCommand{

    /*

    * Cut Command is the class which delegates to the Editor the business
    * logic for cutting the selected node. 
    */

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
