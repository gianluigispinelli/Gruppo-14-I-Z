package drawingSoftware.Shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyBoundingBox extends Rectangle{

    public MyBoundingBox(double x, double y, double width, double height){
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setStroke(Color.AQUA);
        this.setFill(Color.TRANSPARENT);
    };
}
