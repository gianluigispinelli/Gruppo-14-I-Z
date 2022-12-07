package drawingSoftware.Editor;

public class PasteCommand extends EditorAbstractCommand{

    public PasteCommand(Editor editor) {
        super(editor);
    }    

    @Override
    public boolean execute() {
        saveBackup();
        editor.paste();
        return true; 
    }
}

