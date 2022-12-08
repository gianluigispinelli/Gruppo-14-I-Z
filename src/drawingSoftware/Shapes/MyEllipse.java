package drawingSoftware.Shapes;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

public class MyEllipse extends Ellipse implements MyShape{

    @Override
    public double myGetX() {
        return super.getCenterX();
    }

    @Override
    public double myGetY() {
        return super.getCenterY();
    }

    @Override
    public double myGetWidth() {
        return super.getRadiusX();
    }

    @Override
    public double myGetHeight() {
        return super.getRadiusY();
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
