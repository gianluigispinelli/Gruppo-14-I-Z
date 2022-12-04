package drawingSoftware;

public class CopyCommand implements Command{

    Editor editor; 

    public CopyCommand(Editor editor){
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.copy();
    }
}
