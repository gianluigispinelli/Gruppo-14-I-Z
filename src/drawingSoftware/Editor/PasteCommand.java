package drawingSoftware.Editor;

import drawingSoftware.Command;
import javafx.scene.layout.Pane;

public class PasteCommand implements Command{

    Editor editor; 
    Pane drawableWindow;

    public PasteCommand(Editor editor, Pane drawableWindow){
        this.editor = editor;
        this.drawableWindow = drawableWindow; 
    }

    @Override
    public void execute() {
        editor.paste(drawableWindow);
    }
}

