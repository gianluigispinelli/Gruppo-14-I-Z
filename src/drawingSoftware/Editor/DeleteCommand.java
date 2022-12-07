package drawingSoftware.Editor;


public class DeleteCommand extends EditorAbstractCommand{

    public DeleteCommand(Editor editor) {
        super(editor);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean execute() {
        editor.delete();
        return true; 
    }
    
}
