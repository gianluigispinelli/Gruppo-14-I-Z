package drawingSoftware.Command.BackupCommand.EditorCommand;

import drawingSoftware.Command.BackupCommand.BackupCommand;
import drawingSoftware.Editor.Editor;
import javafx.collections.FXCollections;

public abstract class EditorAbstractCommand extends BackupCommand{

    /*
     * Coupling: EditorAbstractCommand depends on Editor. It implements Command.
     */

    protected Editor editor; 
    
    /*
     * Mandatory constructor for all its subclasses
     */
    public EditorAbstractCommand(Editor editor){
        this.editor = editor;
    }

    /*
     * Every command, before executing its operations, save his own backup
     */
    @Override
    public void saveBackup(){
        super.backup = FXCollections.observableArrayList(editor.getModel().getAllShapes());   /* the backup is how the shapes were before exec. the command */
    }

    @Override
    public void undo(){
        editor.getModel().addAll(super.backup);
    }

    @Override
    public boolean execute(){ return false; }
    
}
