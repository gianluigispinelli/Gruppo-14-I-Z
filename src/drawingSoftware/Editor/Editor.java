package drawingSoftware.Editor;

import java.util.List;

import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.ShapeCommand.DrawShapeCommand;
import drawingSoftware.Managers.ResizeTextFieldManager;
import drawingSoftware.Shapes.MyBoundingBox;
import drawingSoftware.Tool.EllipseTool;
import drawingSoftware.Tool.LineTool;
import drawingSoftware.Tool.RectangleTool;
import javafx.geometry.Bounds;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/*

 * Editor is the receiver class which holds
 * the business logic for copying, cutting, pasting, deleting
 * and resizing a shape. 
 * The copied/cut shapes is saved in a specific clipboard which
 * will be used for copying the selected shape in the drawingWindow. 
 * All these functions operate on the nodes of the Model class. 
 */

public class Editor {

    private Shape clipboard;
    private Model model;        /* the state */
    private Pane drawingWindow; 

    public Editor(Model model, Pane drawingWindow){
        this.model = model; 
        this.drawingWindow = drawingWindow;
    }
    public Editor(Model model){
        this.model = model;
    }

    public Model getModel(){
        return this.model;
    }
    
    public void copy(){
            clipboard = (Shape)model.getSelectedShape();
    } 

    public void paste(){
        DrawShapeCommand drawShapeCommand; 
        if (clipboard instanceof Rectangle){    /* if it's a rectangle */
            Rectangle duplicatedRectangle = dupliRectangle((Rectangle)clipboard);
            drawShapeCommand = new DrawShapeCommand(model,duplicatedRectangle);
        }
        else if (clipboard instanceof Line){
            Line duplicatedLine = dupliLine((Line)clipboard);
            drawShapeCommand = new DrawShapeCommand(model, duplicatedLine);
        }
        else if (clipboard instanceof Ellipse){
            Ellipse duplicatedEllipse = dupliEllipse((Ellipse)clipboard);
            drawShapeCommand = new DrawShapeCommand(model,duplicatedEllipse);
        }
        else{
            Polygon duplicatedPolygon = dupliPolygon((Polygon)clipboard);
            drawShapeCommand = new DrawShapeCommand(model,duplicatedPolygon);
        }
        drawShapeCommand.execute();
    }

    public void cut(){
        clipboard = (Shape)model.getSelectedShape();
        model.removeShape(clipboard);
        model.setCurrentShape(null);
    }

    public void delete(){
            model.removeShape(model.getSelectedShape());
            model.setCurrentShape(null);
    }
    /*

     * methods for pasting
     */
    public void edit(ResizeTextFieldManager textFieldManager, MyBoundingBox boundingBox){

        
    /*
      * disable text field when shape is not selected.
      */
        
      double valueTextFieldWight = 0.0;
      double valueTextFieldHeight = 0.0;
      Shape shape = (Shape) model.getSelectedShape();

      valueTextFieldWight = textFieldManager.setValueFieldWidth(valueTextFieldWight);
      valueTextFieldHeight = textFieldManager.setValueFieldHeight(valueTextFieldHeight);

      if(shape!=null && valueTextFieldWight > 0.0 && valueTextFieldHeight > 0.0){
          
        if(shape instanceof Rectangle){
            Rectangle rect=(Rectangle) shape;
            RectangleTool resizeRectangle = new RectangleTool(rect, model, drawingWindow);
            resizeRectangle.setNewDimShape(rect.getWidth(), rect.getHeight(), valueTextFieldWight, valueTextFieldHeight);
            textFieldManager.setFieldWight(resizeRectangle.getWidthRectangle());
            textFieldManager.setFieldHeight(resizeRectangle.getHeightRectangle());
           
        }

        else if(shape instanceof Ellipse){
            Ellipse ellipse = (Ellipse) shape;
            EllipseTool resizeEllipse = new EllipseTool(ellipse, model, drawingWindow);
            resizeEllipse.setNewDimShape(ellipse.getRadiusX(), ellipse.getRadiusY(), valueTextFieldWight, valueTextFieldHeight);
            textFieldManager.setFieldWight(resizeEllipse.getWidthEllipse());
            textFieldManager.setFieldHeight(resizeEllipse.getHeightEllipse());
        
        }else if(shape instanceof Line){
            Line line = (Line) shape;
            LineTool resizeLine = new LineTool(line, model, drawingWindow);
            resizeLine.setNewDimShape(valueTextFieldWight);
            textFieldManager.setFieldWight(resizeLine.getSegmentSize());
        }
          setBoundBox(shape, boundingBox);

      }
    }

    private void setBoundBox(Shape shapeSelected, MyBoundingBox boundingBox){
        boundingBox.setX(shapeSelected.getBoundsInLocal().getMinX());
        boundingBox.setY(shapeSelected.getBoundsInLocal().getMinY());
        boundingBox.setWidth(shapeSelected.getBoundsInLocal().getWidth());
        boundingBox.setHeight(shapeSelected.getBoundsInLocal().getHeight());
    }

    public Rectangle dupliRectangle(Rectangle rectangle){
        Double x = rectangle.getX();
        Double y = rectangle.getY();
        Double width = rectangle.getWidth();
        Double height = rectangle.getHeight();
        Paint stroke = rectangle.getStroke();
        Paint fill = rectangle.getFill();
        
        Rectangle copiedRectangle = new Rectangle();
        copiedRectangle.setX(x+10);
        copiedRectangle.setY(y+10);
        copiedRectangle.setWidth(width);
        copiedRectangle.setHeight(height);
        copiedRectangle.setStroke(stroke);
        copiedRectangle.setFill(fill);
        return copiedRectangle;
    }

    public Ellipse dupliEllipse(Ellipse ellipse){
        Double x = ellipse.getCenterX();
        Double y = ellipse.getCenterY();
        Double width = ellipse.getRadiusX();
        Double height = ellipse.getRadiusY();
        Paint stroke = ellipse.getStroke();
        Paint fill = ellipse.getFill();
        
        Ellipse copiedEllipse = new Ellipse();
        copiedEllipse.setCenterX(x+10);
        copiedEllipse.setCenterY(y+10);
        copiedEllipse.setRadiusX(width);
        copiedEllipse.setRadiusY(height);
        copiedEllipse.setStroke(stroke);
        copiedEllipse.setFill(fill);
        return copiedEllipse;
    }

    public Line dupliLine(Line line){
        Double startX = line.getStartX();
        Double startY = line.getStartY();
        Double endX = line.getEndX();
        Double endY = line.getEndY();
        Paint stroke = line.getStroke();

        Line copiedLine = new Line();
        copiedLine.setStartX(startX+10);
        copiedLine.setStartY(startY+10);
        copiedLine.setEndX(endX+10);
        copiedLine.setEndY(endY+10);
        copiedLine.setStroke(stroke);
        return copiedLine;
    }

    public Polygon dupliPolygon(Polygon poly){

        Polygon copiedPoly = new Polygon();

        copiedPoly.getPoints().addAll(poly.getPoints());
        copiedPoly.setStroke(poly.getStroke());
        copiedPoly.setFill(poly.getFill());
        copiedPoly.setLayoutX(poly.getLayoutX());
        copiedPoly.setLayoutY(poly.getLayoutY());

        return copiedPoly; 
    }

    /*

     * getter for clipboard. It's for testing purposes.  
     */
    public Shape getClipboard() {
        return clipboard;
    }
}
