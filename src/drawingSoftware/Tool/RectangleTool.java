package drawingSoftware.Tool;


import drawingSoftware.Model;
import drawingSoftware.Editor.DrawShapeCommand;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class RectangleTool implements Tool{
    private Model model; 

    private Rectangle rectangle;

    private Pane drawingWindow;

    private double finalDragX;
    private double startDragX;
    private double finalDragY;
    private double startDragY;
    private ColorPicker borderColor;
    private ColorPicker fillColor;

    public RectangleTool(Model model, Pane drawingWindow) {
        this.model = model; 
        this.drawingWindow = drawingWindow;
    }



    @Override
    public void useSelectedTool(ColorPicker borderColor, ColorPicker fillColor) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        this.captureMouseEvent();
           
    }

    private void createRectangle(){
        this.rectangle = new Rectangle();
        this.setDim(startDragX, finalDragX, startDragY, finalDragY);
        this.rectangle.setFill(fillColor.getValue());
        this.rectangle.setStroke(borderColor.getValue());
        this.drawRectangle();
    }

    private void captureMouseEvent(){

        drawingWindow.setOnMousePressed(e -> {
            
            if(e.getButton() == MouseButton.PRIMARY){
                this.setStartDragX(e.getX());
                this.setStartDragY(e.getY());
            }
            e.consume();
        });
        //event filter because mouse move quickly ad 
        drawingWindow.setOnMouseReleased(e ->{

                if(e.getButton() == MouseButton.PRIMARY){
                    this.setFinalDragX(e.getX());
                    this.setFinalDragY(e.getY());
                    this.createRectangle();
                    e.consume();
                }
            
        });
    }



    private void drawRectangle(){
        DrawShapeCommand drawCommand = new DrawShapeCommand(model, this.drawingWindow, this.rectangle);
        drawCommand.execute();
    }



    private void setDim(double startDragX, double finalDragX, double startDragY, double finalDragY){

        double PointsX[] = this.checkDrawStartPoint(startDragX, finalDragX);
        double PointsY[] = this.checkDrawStartPoint(startDragY, finalDragY);
        
        double width = this.calculateDim(startDragX, finalDragX);
        double height = this.calculateDim(startDragY, finalDragY);

        finalDragX = finalDragX < 0.0 ? 0.0 : finalDragX;
        startDragX = startDragX < 0.0 ? 0.0 : startDragX;
        finalDragY = finalDragY < 0.0 ? 0.0 : finalDragY;
        startDragY = startDragY < 0.0 ? 0.0 : startDragY;

        this.rectangle.setX(PointsX[0]);
        this.rectangle.setY(PointsY[0]);

        this.rectangle.setWidth(width);
        this.rectangle.setHeight(height);
    }



    private double[] checkDrawStartPoint(double start, double end){
        double startPoint, endPoint;
        if (start < end){
            startPoint = start;
            endPoint = end;   
        }
        else{
            startPoint = end;
            endPoint = start;    
        }
        double points[] = {startPoint, endPoint};
        return points;
    }



    private double calculateDim(double startPoint, double endPoint){
        return(Math.abs(endPoint - startPoint));
    }



    @Override
    public ObservableBooleanValue isNotLineTool() {
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }



    public void setFinalDragX(double finalDragX) {
        this.finalDragX = finalDragX;
    }



    public void setStartDragX(double startDragX) {
        this.startDragX = startDragX;
    }



    public void setFinalDragY(double finalDragY) {
        this.finalDragY = finalDragY;
    }



    public void setStartDragY(double startDragY) {
        this.startDragY = startDragY;
    }
}