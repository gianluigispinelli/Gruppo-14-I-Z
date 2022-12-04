package drawingSoftware.Editor;

import drawingSoftware.Load.Save.Command;
import javafx.scene.layout.Pane;

public class CopyCommand implements Command{

    Editor editor; 
    Pane drawableWindow;

    public CopyCommand(Editor editor, Pane drawableWindow){
        this.editor = editor;
        this.drawableWindow = drawableWindow;
    }

    @Override
    public void execute() {
        editor.copy(drawableWindow);
    }
}
