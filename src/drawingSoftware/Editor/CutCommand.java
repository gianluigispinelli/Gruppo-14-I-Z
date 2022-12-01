package drawingSoftware.Editor;

import drawingSoftware.Command;
import javafx.scene.layout.Pane;

public class CutCommand implements Command{

    Editor editor; 
    Pane drawableWindow;

    public CutCommand(Editor editor, Pane drawableWindow){
        this.editor = editor;
        this.drawableWindow = drawableWindow;
    }

    @Override
    public void execute() {
        editor.cut(drawableWindow);
    }
}
