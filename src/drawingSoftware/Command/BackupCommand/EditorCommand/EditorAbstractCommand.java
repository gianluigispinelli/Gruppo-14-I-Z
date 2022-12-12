package drawingSoftware.Command.BackupCommand.EditorCommand;

import drawingSoftware.Command.BackupCommand.BackupCommand;
import drawingSoftware.Editor.Editor;
import javafx.collections.FXCollections;

public abstract class EditorAbstractCommand extends BackupCommand{

    /*

    * EditorAbstractCommand is an abstract class for those commands which
    * delegate the business logic to the Editor class playing the role of
    * the Receiver. Every son of this class will inherit the method for
    * saving his own backup, which is the current state of the Model
    * in terms of the current nodes on the drawingWindow. 
    * The sons also will inherit the method undo() for restoring
    * the state they saved via updated the model nodes.  
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
    public abstract boolean execute();

    public Editor getEditor(){
        return this.editor; 
    }    
}
