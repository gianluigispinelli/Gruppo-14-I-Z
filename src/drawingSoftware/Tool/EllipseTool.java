package drawingSoftware.Tool;

import drawingSoftware.Controller;
import drawingSoftware.Model;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;


/* Class that extends toolShape abstract class. This class is responsible for drawing Ellipse.
 It is a concrete state of State pattern */


public class EllipseTool extends ShapeTool{
    private Controller controller; 
    private Ellipse ellipse;
    private ColorPicker borderColor;
    private ColorPicker fillColor;


    public EllipseTool(Ellipse ellipse, Model model, Pane drawingWindow){
        super(model, drawingWindow);
        this.ellipse = ellipse;
    }

        
    public EllipseTool(Controller controller, Model model, Pane drawingWindow, ColorPicker borderColor, ColorPicker fillColor) {
        super(controller, model, drawingWindow);
        this.controller = controller; 
        this.borderColor = borderColor;
        this.fillColor = fillColor; 
        super.deselectAllShape();
    }

    @Override
    public void useSelectedTool() {        
        //super.setDrawParameters();
        this.createShape();
    }

    // Method that instantiate ellipse and set fill and border color.
    @Override
    public void createShape(){
        double startDragX = super.getStartDragX();
        double finalDragX = super.getFinalDragX();
        double startDragY = super.getStartDragY();
        double finalDragY = super.getFinalDragY();

        this.ellipse = new Ellipse();
        this.setDim(startDragX, finalDragX, startDragY, finalDragY);
        this.ellipse.setFill(fillColor.getValue());
        this.ellipse.setStroke(borderColor.getValue());
        super.drawShape(ellipse);
        this.rotate(ellipse);
        
    }

    // Method for setting the attributes of the ellipse by taking in input the coordinates of the points
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

        this.ellipse.setCenterX(startDragX + Math.abs(finalDragX - startDragX)/2);
        this.ellipse.setCenterY(startDragY+  Math.abs(finalDragY - startDragY)/2);

        this.ellipse.setRadiusX(width/2.0);
        this.ellipse.setRadiusY(height/2.0);
    }

    private double calculateDim(double startPoint, double endPoint){
        return(Math.abs(endPoint - startPoint));
    }
    
    @Override
    public void setNewDimShape(double oldWigth, double oldHeight, double newWidth, double newHeight){
        
        super.setNewDimShape(oldWigth, oldHeight, newWidth, newHeight);
        this.ellipse.setRadiusX(super.getFinalDragX());
        this.ellipse.setRadiusY(super.getFinalDragY());
        
    }
    
    @Override
    public ObservableBooleanValue isNotLineTool() {
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }

    public void rotate(Ellipse e){
        e.setRotate(0);
        e.setScaleX(-1);
    }
    public double getWidthEllipse(){
        return super.approximateDoubleValue(this.ellipse.getRadiusX());
    }
    public double getHeightEllipse(){
        return super.approximateDoubleValue(this.ellipse.getRadiusY());
    }
    public Ellipse getEllipse(){
        return this.ellipse;
    }
    public void setEllipse(Ellipse ellipse){
        this.ellipse = ellipse;
    }
}