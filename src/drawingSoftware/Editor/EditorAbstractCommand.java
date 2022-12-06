package drawingSoftware.Editor;

import drawingSoftware.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public abstract class EditorAbstractCommand implements Command{

    protected Controller controller; 
    protected Editor editor; 
    protected ObservableList<Node> backup; 

    /*
     * Mandatory constructor for all its subclasses
     */
    public EditorAbstractCommand(Controller controller,Editor editor){
        this.controller = controller;
        this.editor = editor;
    }

    /*
     * Every command, before executing its operations, save his own backup
     */
    public void saveBackup(){
        backup = FXCollections.observableArrayList(editor.getModel().getAllShapes());   /* the backup is how the shapes were before exec. the command */
    }

    public void undo(){
        editor.getModel().addAll(backup);
    }

    @Override
    public boolean execute(){ return false; }
    
}
