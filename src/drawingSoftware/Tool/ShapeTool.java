package drawingSoftware.Tool;


import drawingSoftware.Controller;
import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.ShapeCommand.DrawShapeCommand;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

//import javafx.scene.control.ColorPicker;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/*
 * ShapeTool is the abstract class extended by the tool resposnsible of drawing Ellipse, Rectangle and Line.
 * The attributes startDragX, startDragY, finalDragX and finalDragY are of type double and,
 *  in couple (startDragX, startDragY) and (finalDragX, finalDragY), are the coordinate of the points in which the drag starts and ends.
 * The method setToolParameters allow to set the value of the attributes mentioned above. Furthermore, these attributes these attributes 
 * are used to set the size of the figure to be drawn.
 */

public abstract class ShapeTool extends AbstractTool{
    protected double finalDragX;
    protected double startDragX;
    protected double finalDragY;
    protected double startDragY;
    protected Pane drawingWindow;
    protected Pane draftPane; //added
    protected Controller controller; 
    //private ColorPicker borderColor;
    //private ColorPicker fillColor;

    private Model model;
    //static boolean flag = false;
    
    // The method createShape is an abstract method that has to be override 
    // in the concrete class according to the tool selected for drawing.
    public abstract void createShape();

    // Constructor
    public ShapeTool(Model model,Pane drawingWindow) {
        
        this.model = model;
        this.drawingWindow = drawingWindow;

    }

    // Constructor
    public ShapeTool(Controller controller, Model model,Pane drawingWindow) {
        
        this.controller = controller; 
        this.model = model;
        this.drawingWindow = drawingWindow;
        this.deselectAllShape();
        drawingWindow.setCursor(Cursor.CROSSHAIR);

    }

     // Utility method that if a shape is selected, when the user click a tool to draw, then it deselect the selected figure.
    protected void deselectAllShape(){
        Shape deselectShape = (Shape)model.getSelectedShape();
        if(deselectShape != null){
            deselectShape.strokeProperty().unbind();
            deselectShape.fillProperty().unbind();
        }
        model.setCurrentShape(null);
    }

    // The method setToolParameters take in input two mouse event, pressed and released,
    // according to which it set the start drawing point
    // and end drawing point. 
    @Override
    public void setToolParameters(MouseEvent e1, MouseEvent e2){
        if(e1.getButton() == MouseButton.PRIMARY){
            setStartDragX(e1.getX());
            setStartDragY(e1.getY());
        }
        if(e2.getButton() == MouseButton.PRIMARY){
            setFinalDragX(e2.getX());
            setFinalDragY(e2.getY());
        }
    }

    /* setDim is an utility method that set the dimensions of the shape to draw and it does some check 
       that are useful to make the user draw only on the drawing window. The input parameters are the 
       startDragX, startDragY, finalDragX and finalDragY attributes.
    */
    public void setDim(double startDragX, double finalDragX, double startDragY, double finalDragY){
        
        finalDragX = finalDragX < 0.0 ? 0.0 : finalDragX;
        startDragX = startDragX < 0.0 ? 0.0 : startDragX;
        finalDragY = finalDragY < 0.0 ? 0.0 : finalDragY;
        startDragY = startDragY < 0.0 ? 0.0 : startDragY;

        if(finalDragX > drawingWindow.getMaxWidth()){
            finalDragX = drawingWindow.getMaxWidth();
            drawingWindow.setPrefWidth(finalDragX);

        }else if(finalDragX > drawingWindow.getPrefWidth() && !(finalDragX > drawingWindow.getMaxWidth())){
            drawingWindow.setPrefWidth(finalDragX);
        }

        if(finalDragY > drawingWindow.getMaxHeight()){
            finalDragY = drawingWindow.getMaxHeight();
            drawingWindow.setPrefHeight(finalDragY);
        
        }else if(finalDragY > drawingWindow.getPrefHeight()  && !(finalDragY > drawingWindow.getMaxHeight())){
                drawingWindow.setPrefHeight(finalDragY);
        }

        double PointsX[] = this.checkDrawStartPoint(startDragX, finalDragX);
        double PointsY[] = this.checkDrawStartPoint(startDragY, finalDragY);
        this.startDragX = PointsX[0];
        this.finalDragX = PointsX[1];
        this.startDragY = PointsY[0];
        this.finalDragY = PointsY[1];
    }


    /* The  following method instantiate a drawShapeCommand and delegate to it the effective creation of the shape.
     * It takes in input the reference to the shape that has to be draw.
    */
    public  void drawShape(Shape shape){
        // The model is provided as parameter because the DrawShapeCommand 
        // add the shape both to the drawing window and to the ObservableList
        // of shapes that are in the drawingWindow.
        DrawShapeCommand drawCommand = new DrawShapeCommand(this.model,shape);
        controller.executeCommand(drawCommand);

    }

    public void setNewDimShape(double oldWight, double oldHeight, double newWidth, double newHeight){
        double ratio = approximateDoubleValue(oldHeight)/approximateDoubleValue(oldWight);
        
        if(newWidth > this.drawingWindow.getPrefWidth() || newHeight > this.drawingWindow.getPrefHeight()){
            this.setDim(oldWight, newWidth, oldHeight, newHeight);
            newWidth = this.finalDragX;
            newHeight = this.finalDragY;
        }

        if(newWidth != oldWight && newHeight == oldHeight){
            this.finalDragX = newWidth;
            this.finalDragY = newWidth*ratio;
        }
        else if(newHeight != oldHeight && newWidth == oldWight){
            this.finalDragX = newHeight/ratio;
            this.finalDragY = newHeight;
        }
        else{
            this.finalDragX = newWidth;
            this.finalDragY = newHeight;
        }
    }
   
    //The method set as start point the point with the lowest value and as end point the one whose value are highest.
    public  double[] checkDrawStartPoint(double start, double end){
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


    public double getFinalDragX() {
        return finalDragX;
    }


    public double getStartDragX() {
        return startDragX;
    }


    public double getFinalDragY() {
        return finalDragY;
    }


    public double getStartDragY() {
        return startDragY;
    }


    public Pane getDrawingWindow() {
        return drawingWindow;
    }


    public Model getModel() {
        return model;
    }
    

    protected double approximateDoubleValue(double number){
        return Math.round(number*100.00)/100.00;
    }
}
