package drawingSoftware.Editor;

public class CutCommand extends EditorAbstractCommand{

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
