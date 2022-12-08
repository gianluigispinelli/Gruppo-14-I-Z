package drawingSoftware.Command.BackupCommand.ShapeCommand;

import drawingSoftware.Model;
import javafx.scene.shape.Shape;

public class MoveCommand extends ShapeCommand{

    private double startX;
    private double startY;
    private double endX;
    private double endY;

    public MoveCommand(Model model, Shape shapeToMove, double startX, double startY, double endX, double endY) {
        super(model, shapeToMove);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public boolean execute() {
        saveBackup();
        super.shape.setTranslateX(shape.getTranslateX() + endX - startX);
        this.shape.setTranslateY(shape.getTranslateY() + endY - startY );
        return true; 
    }
}
