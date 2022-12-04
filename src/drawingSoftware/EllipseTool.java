package drawingSoftware;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;

public class EllipseTool implements Tool{
    private Model model; 
    private Ellipse ellipse;
    private Pane drawingWindow;
    private double finalDragX;
    private double startDragX;
    private double finalDragY;
    private double startDragY;
    private ColorPicker borderColor;
    private ColorPicker fillColor;    

    public EllipseTool(Model model, Pane drawingWindow) {
        this.model = model; 
        this.drawingWindow = drawingWindow;
    }

    

    @Override
    public void useSelectedTool(ColorPicker borderColor, ColorPicker fillColor) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        this.captureMouseEvent();

    }

    private void createEllipse(){
        this.ellipse = new Ellipse();
        this.setDim(startDragX, finalDragX, startDragY, finalDragY);
        this.ellipse.setFill(fillColor.getValue());
        this.ellipse.setStroke(borderColor.getValue());
        this.drawEllipse();
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
                    this.createEllipse();
                    e.consume();
                }
        });
    }


    
    public void setDim(double startDragX, double finalDragX, double startDragY, double finalDragY){
        
        double PointsX[] = this.checkDrawStartPoint(startDragX, finalDragX);
        double PointsY[] = this.checkDrawStartPoint(startDragY, finalDragY);
        
        double width = this.calculateDim(startDragX, finalDragX);
        double height = this.calculateDim(startDragY, finalDragY);

        finalDragX = finalDragX < 0.0 ? 0.0 : finalDragX;
        startDragX = startDragX < 0.0 ? 0.0 : startDragX;
        finalDragY = finalDragY < 0.0 ? 0.0 : finalDragY;
        startDragY = startDragY < 0.0 ? 0.0 : startDragY;

        this.ellipse.setCenterX(PointsX[0] + Math.abs(finalDragX - startDragX)/2);
        this.ellipse.setCenterY(PointsY[0] +  Math.abs(finalDragY - startDragY)/2);

        this.ellipse.setRadiusX(width/2.0);
        this.ellipse.setRadiusY(height/2.0);
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



    private void drawEllipse(){
        DrawShapeCommand drawCommand = new DrawShapeCommand(model, this.drawingWindow,this.ellipse);
        drawCommand.execute();
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