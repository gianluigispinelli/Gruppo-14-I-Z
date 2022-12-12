package drawingSoftware.Command.BackupCommand.EditorCommand;

import drawingSoftware.Model;
import drawingSoftware.Editor.Editor;
import drawingSoftware.Managers.ResizeTextFieldManager;
import drawingSoftware.Shapes.MyBoundingBox;
import javafx.scene.control.TextField;
import javafx.scene.shape.Shape;

/*

 * EditShapeCommand is the Command class which holds the business logic for resizing a figure.
 * Before doing that, it saved the current state of the Model. 
 * This class delegates to Editor's edit() method.
 */

public class EditShapeCommand extends EditorAbstractCommand{

    private ResizeTextFieldManager textFieldManager;
    private MyBoundingBox boundingBox;

    public EditShapeCommand(
        Editor editor, 
        ResizeTextFieldManager textFieldManager,
        MyBoundingBox boundingBox){
        super(editor);
        this.textFieldManager = textFieldManager;
        this.boundingBox = boundingBox;
    }

    @Override
    public boolean execute() {
        saveBackup();
        editor.edit(textFieldManager, boundingBox);
        return true;
    }
}
    
