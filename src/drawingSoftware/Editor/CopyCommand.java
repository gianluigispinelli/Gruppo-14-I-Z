package drawingSoftware.Editor;

public class CopyCommand extends EditorAbstractCommand{

    public CopyCommand(Editor editor) {
        super(editor);
    }

    public boolean execute(){
        // app.clipboard ? 
        editor.copy();
        return false; 
    }
}
