package drawingSoftware.Tool;


import drawingSoftware.Controller;
import drawingSoftware.Model;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;


/* Class that extends toolShape abstract class. T
his class is responsible for drawing Line. 
It is a concrete state of State pattern */

public class LineTool extends ShapeTool{
    private Controller controller; 
    private Model model; 
    private Pane drawingWindow;
    private Line line;
    private ColorPicker borderColor;

    public LineTool(Line line, Model model, Pane drawingWindow){
        super(model, drawingWindow);
        this.line = line;
    }

    public LineTool(Controller controller, Model model,Pane drawingWindow, ColorPicker borderColor) {
        super(controller, model, drawingWindow);
        this.controller = controller; 
        this.borderColor = borderColor;
        //this.captureMouseEvent();
        super.deselectAllShape();
    }


    // Method for setting the dimension  of the Rectangle whose diagonal is the Line to draw by taking in input the coordinates of the points
    // in which the draw starts and ends.
    public void setDim(double startDragX, double finalDragX, double startDragY, double finalDragY){
       //super.setDim(startDragX, finalDragX, startDragY, finalDragY);
        
        startDragX = super.getStartDragX();
        startDragY = super.getStartDragY();
        finalDragX = super.getFinalDragX();
        finalDragY = super.getFinalDragY();
        
        this.setXDim(startDragX, finalDragX);
        this.setYDim(startDragY, finalDragY);
    }

    private void setXDim(double xStartValue, double xEndValue){
        this.line.setStartX(xStartValue);
        this.line.setEndX(xEndValue);
    }

    private void setYDim(double yValue, double yEndValue){
        this.line.setStartY(yValue);
        this.line.setEndY(yEndValue);  
    }


    @Override
    public void useSelectedTool() {
        this.createShape();
    }

    public void setNewDimShape(double newWidth){
        
        double segmentWidth = calculateSegmentValue(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        double a = line.getEndX() - line.getStartX();
        double b = line.getEndY() - line.getStartY();

        double scalarFactor = newWidth/segmentWidth;
        double newEndX = line.getStartX() + a*scalarFactor;
        double newEndY = line.getStartY() + b*scalarFactor;
        line.setEndX(newEndX);
        line.setEndY(newEndY);
        
    }


    @Override
    public ObservableBooleanValue isNotLineTool() {
        ObservableBooleanValue visible = new SimpleBooleanProperty(false);
        return visible;
    }


    @Override
    public void createShape() {
        this.line = new Line();
        this.setDim(startDragX, finalDragX, startDragY, finalDragY);
        this.line.setStroke(borderColor.getValue());
        super.drawShape(line);
        
    }

    private double calculateSegmentValue(double startX, double startY, double endX, double endY){
        double segmentValue = Math.sqrt(Math.pow((endY - startY), 2) + Math.pow((endX - startX), 2));
        return super.approximateDoubleValue(segmentValue);
    }
    
    public double getSegmentSize(){
        return calculateSegmentValue(startDragY, startDragX, finalDragY, finalDragX);
    }
    public void setLine(Line line){
        this.line = line;
    }
    public Line getLine(){
        return this.line;
    }
}