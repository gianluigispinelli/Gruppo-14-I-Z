package drawingSoftware.Shapes;

import javafx.scene.paint.Paint;

public interface MyShape {    

    public double myGetX();
    public double myGetY();
    public double myGetWidth();
    public double myGetHeight();
    public Paint myGetStroke();
    public Paint myGetFill();

    
}
