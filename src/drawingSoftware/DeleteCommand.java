package drawingSoftware;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class DeleteCommand implements Command{
    private Pane receiverPane;
    

    
    public DeleteCommand(Pane receiverPane) {
        this.receiverPane = receiverPane;
    }



    @Override
    public void execute() {
        Node border = receiverPane.lookup("#selected");
        //Node shape = receiverPane.lookup("#selectedShape");
        System.out.println("ifwwww");
        if (border != null ){
            System.out.println("if");
            receiverPane.getChildren().remove(border);
            //receiverPane.getChildren().remove(shape);
        }
        
    }
    
}
