package drawingSoftware.Shapes;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MyRectangle extends Rectangle implements MyShape{

    public double myGetX(){
        return super.getX();
    }

    @Override
    public double myGetY() {
        return super.getY();
    }

    @Override
    public double myGetWidth() {
        return super.getWidth();
    }

    @Override
    public double myGetHeight() {
        return super.getHeight();
    }

    @Override
    public Paint myGetStroke() {
        return super.getStroke();
    }

    @Override
    public Paint myGetFill() {
        return super.getFill();
    }
    
    
}
