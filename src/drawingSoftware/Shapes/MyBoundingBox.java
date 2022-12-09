package drawingSoftware.Shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyBoundingBox extends Rectangle{

    public MyBoundingBox(double x, double y, double width, double height){
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setStroke(Color.rgb(0, 128, 255));
        this.setStrokeWidth(5);
        this.getStrokeDashArray().addAll(3.0, 7.0, 3.0, 7.0);
        this.setFill(Color.TRANSPARENT);
    };
}
