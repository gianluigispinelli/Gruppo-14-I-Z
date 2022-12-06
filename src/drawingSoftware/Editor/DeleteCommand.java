package drawingSoftware.Editor;

import drawingSoftware.Model;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class DeleteCommand implements Command{
    private Model model; 
    private Pane receiverPane;
    

    
    public DeleteCommand(Model model, Pane receiverPane) {
        this.model = model; 
        this.receiverPane = receiverPane;
    }



    @Override
    public boolean execute() {
        
        Node border = receiverPane.lookup("#selected");
        if (border != null ){
            model.removeShape(border);
        }
        return true; 
    }
    
}
