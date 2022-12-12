package drawingSoftware.Tool;


import drawingSoftware.Controller;
import drawingSoftware.Model;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


/* Class that extends toolShape abstract class. This class
 is responsible for drawing Rectangle. It is a concrete state of State pattern */

public class RectangleTool extends ShapeTool{
    private Rectangle rectangle;
    private ColorPicker borderColor;
    private ColorPicker fillColor;
    private Controller controller; 

    public RectangleTool(Rectangle rectangle, Model model, Pane drawingWindow){
        super(model, drawingWindow);
        this.rectangle = rectangle;
    }
        
    public RectangleTool(Controller controller, Model model, Pane drawingWindow, ColorPicker borderColor, ColorPicker fillColor) {
        super(controller, model, drawingWindow);
        this.controller = controller; 
        this.borderColor = borderColor;
        this.fillColor = fillColor;  
        super.deselectAllShape();

    }

    @Override
    public void useSelectedTool() {
        this.createShape();
    }

    // Method that instantiate rectangle and set fill and border color.
    @Override
    public void createShape(){
        double startDragX = super.getStartDragX();
        double finalDragX = super.getFinalDragX();
        double startDragY = super.getStartDragY();
        double finalDragY = super.getFinalDragY();
       

        this.rectangle = new Rectangle();
        
        this.setDim(startDragX, finalDragX, startDragY, finalDragY);
        this.rectangle.setFill(fillColor.getValue());
        this.rectangle.setStroke(borderColor.getValue());
        // Call to the superclass method
        super.drawShape(this.rectangle);
        
    }
    

    // Method for setting the width and height attribute of the Rectangle by taking in input the coordinates of the points
    // in which the draw starts and ends.
    @Override
    public void setDim(double startDragX, double finalDragX, double startDragY, double finalDragY){

        super.setDim(startDragX, finalDragX, startDragY, finalDragY);

        startDragX = super.getStartDragX();
        startDragY = super.getStartDragY();
        finalDragX = super.getFinalDragX();
        finalDragY = super.getFinalDragY();

        double width = this.calculateDim(startDragX,finalDragX);
        double height = this.calculateDim(startDragY, finalDragY);

        this.rectangle.setX(startDragX);
        this.rectangle.setY(startDragY);

        this.rectangle.setWidth(width);
        this.rectangle.setHeight(height);
    }

    @Override
    public void setNewDimShape(double oldWidth, double oldHeight, double newWidth, double newHeight){
        
        //super.setNewDimShape(this.rectangle.getWidth(), this.rectangle.getHeight(), newWidth, newHeight);
        super.setNewDimShape(oldWidth, oldHeight, newWidth, newHeight);
        this.rectangle.setWidth(super.getFinalDragX());
        this.rectangle.setHeight(super.getFinalDragY());
    }
    

    // Utility method for calculate width and height of the rectangle
    private double calculateDim(double startPoint, double endPoint){
        return(Math.abs(endPoint - startPoint));
    }


    @Override
    public ObservableBooleanValue isNotLineTool() {
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }
    public double getWidthRectangle(){
        return super.approximateDoubleValue(this.rectangle.getWidth());
    }
    public double getHeightRectangle(){
        return super.approximateDoubleValue(this.rectangle.getHeight());
    }
    public Rectangle getRectangle(){
        return this.rectangle;
    }
    public void setRectangle(Rectangle rectangle){
        this.rectangle = rectangle;
    }
}