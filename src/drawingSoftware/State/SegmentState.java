package drawingSoftware.State;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class SegmentState implements State{

    @Override
    public void drawShape(Pane drawingWindow, ColorPicker borderColorPicker,ColorPicker interiorColorPicker,double startDragX, double startDragY, double finalDragX, double finalDragY) {

        finalDragX = finalDragX < 0.0 ? 0.0 : finalDragX;
        startDragX = startDragX < 0.0 ? 0.0 : startDragX;
        finalDragY = finalDragY < 0.0 ? 0.0 : finalDragY;
        startDragY = startDragY < 0.0 ? 0.0 : startDragY;

        Line line = new Line();
        
        line.setStartX(startDragX);
        line.setEndX(finalDragX);

        line.setStartY(startDragY);
        line.setEndY(finalDragY);

        line.setStroke(borderColorPicker.getValue());   

        line.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                double width = line.getLayoutBounds().getWidth();
                    double height = line.getLayoutBounds().getHeight();

                    double x, y; 

                    if (line.getStartX() < line.getEndX()){
                        x = line.getStartX();
                    }else{
                        x = line.getEndX();
                    }

                    if (line.getStartY() < line.getEndY()){
                        y = line.getStartY();  
                    }else{
                        y = line.getEndY();
                    }
                    
                    /*
                     * border: questo rettangolo evidenzia la figura selezionata
                     * vengono prese anche le dimensioni del bordo, nel caso in cui la figura ha un bordo più spesso.
                     * (x,y) del border sono le coordinate del punto in alto a sinistra.
                     * per fare in modo che comprende anche il bordo più spesso viene sottratta alle coord (x,y)
                     * la dimensione della strokewidth della figura selezionata e divide per 2. 
                     */

                    Rectangle border = new Rectangle();
                    border.setId("selected");
                    border.setWidth(width);
                    border.setHeight(height);
                    border.setX(x);
                    border.setY(y);
                    border.setFill(javafx.scene.paint.Color.TRANSPARENT);
                    border.setStroke(javafx.scene.paint.Color.BLUE);
                    border.getStrokeDashArray().addAll(25d, 10d);

                    
                    Node removeBorder = drawingWindow.lookup("#selected");
                    Node changeId = drawingWindow.lookup("#selectedShape");
                    if (changeId!=null)
                    changeId.setId("");
                    line.setId("selectedShape");
                    drawingWindow.getChildren().remove(removeBorder);
                    drawingWindow.getChildren().add(border);
            }
            
        });
        
        drawingWindow.getChildren().addAll(line);
    }
    @Override
    public ObservableBooleanValue isNotSegmentState() {
        ObservableBooleanValue visible = new SimpleBooleanProperty(false);
        return visible;
    }
    
}