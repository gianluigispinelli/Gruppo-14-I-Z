package drawingSoftware.Tool;

import javafx.scene.input.MouseEvent;

public  abstract class AbstractTool implements Tool{
    public abstract void useSelectedTool();
    public abstract void setToolParameters(MouseEvent e1, MouseEvent e2);


    
}
