package drawingSoftware.Editor;

import drawingSoftware.Model;
import javafx.scene.shape.Shape;

public class MoveCommand implements Command{
    private Model model; 
    private Shape shapeToMove;
    private double startX;
    private double startY;
    private double endX;
    private double endY;

    public MoveCommand(Model model, Shape shapeToMove, double startX, double startY, double endX, double endY) {
        this.model = model; 
        this.shapeToMove = shapeToMove;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public void execute() {
        this.shapeToMove.setTranslateX(shapeToMove.getTranslateX() + endX - startX);
        this.shapeToMove.setTranslateY(shapeToMove.getTranslateY()+ endY - startY );
    }
    
}
