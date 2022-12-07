package drawingSoftware.Editor;

import javafx.collections.FXCollections;

public abstract class EditorAbstractCommand extends BackupCommand{

    /*
     * Coupling: EditorAbstractCommand depends on Controller and Editor. It implements Command.
     * Coesion: Editor has 2 methods, saveBackup and undo highly related.
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
