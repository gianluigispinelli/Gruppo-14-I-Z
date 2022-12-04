package drawingSoftware;

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
    public void execute() {
        Node border = receiverPane.lookup("#selected");
        if (border != null ){
            model.removeShape(border);
        }
        
    }
    
}
