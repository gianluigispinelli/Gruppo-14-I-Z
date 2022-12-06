package drawingSoftware.Editor;

import drawingSoftware.Model;
import javafx.collections.FXCollections;
import javafx.scene.shape.Shape;

public class MoveCommand extends BackupCommand{

    private Model model; 
    private Shape shapeToMove;
    private double startX;
    private double startY;
    private double endX;
    private double endY;

    @Override
    public void saveBackup(){
        super.backup = FXCollections.observableArrayList(model.getAllShapes());   /* the backup is how the shapes were before exec. the command */
    }

    @Override
    public void undo(){
        model.addAll(super.backup);
    }

    public MoveCommand(Model model, Shape shapeToMove, double startX, double startY, double endX, double endY) {
        this.model = model; 
        this.shapeToMove = shapeToMove;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public boolean execute() {
        saveBackup();
        model.removeShape(shapeToMove);
        this.shapeToMove.setTranslateX(shapeToMove.getTranslateX() + endX - startX);
        this.shapeToMove.setTranslateY(shapeToMove.getTranslateY()+ endY - startY );
        model.addShape(shapeToMove);
        return true; 
    }
    
}
