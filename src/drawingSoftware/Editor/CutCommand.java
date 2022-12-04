package drawingSoftware.Editor;

public class CutCommand implements Command{

    Editor editor; 

    public CutCommand(Editor editor){
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.cut();
    }
}
