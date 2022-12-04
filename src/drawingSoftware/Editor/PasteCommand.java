package drawingSoftware.Editor;

public class PasteCommand implements Command{

    Editor editor; 

    public PasteCommand(Editor editor){
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.paste();
    }
}

