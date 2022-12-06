package drawingSoftware.Editor;

import drawingSoftware.Controller;
import javafx.scene.shape.Shape;

public class CutCommand extends EditorAbstractCommand{

    public CutCommand(Controller controller, Editor editor) {
        super(controller, editor);
    }
    
    @Override
    public boolean execute() {
        saveBackup();   
        editor.cut();
        return true; 
    }
}
