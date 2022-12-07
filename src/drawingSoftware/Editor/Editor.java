package drawingSoftware.Editor;



import drawingSoftware.Model;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/*
 * Receiver and Originator 
 */
public class Editor {

    private Pane drawingWindow; 
    private Shape copiedShape;
    private Model model;        /* the state */

    public Editor(Model model, Pane drawingWindow){
        this.model = model; 
        this.drawingWindow = drawingWindow;
    }

    public Model getModel(){
        return this.model;
    }

    public Pane getDrawingWindow(){
        return this.drawingWindow;
    }

    public Shape getCopiedShape(){
        return this.copiedShape;
    }

    public void setCopiedShape(Shape shape){
        this.copiedShape = shape;
    }
    
    public void copy(){
        /* dopo copy non bisogna salvare uno snapshot perché non cambia nulla */
        if (drawingWindow.lookup("#selected")!=null){  // se abbiamo selezionato una figura
            copiedShape = (Shape)drawingWindow.lookup("#selected");  //prendo questa figura
        }
    } 

    public void paste(){
        DrawShapeCommand drawShapeCommand; 
        if (copiedShape instanceof Rectangle){    // se è un rettangolo
            Rectangle duplicatedRectangle = dupliRectangle((Rectangle)copiedShape);
            drawShapeCommand = new DrawShapeCommand(model,duplicatedRectangle);
        }
        else if (copiedShape instanceof Line){
            Line duplicatedLine = dupliLine((Line)copiedShape);
            drawShapeCommand = new DrawShapeCommand(model, duplicatedLine);
        }
        else{
            Ellipse duplicatedEllipse = dupliEllipse((Ellipse)copiedShape);
            drawShapeCommand = new DrawShapeCommand(model,duplicatedEllipse);
            
        }
        drawShapeCommand.execute();
    }

    public void cut(){
        if (drawingWindow.lookup("#selected")!=null){  // se abbiamo selezionato una figura
            copiedShape = (Shape)drawingWindow.lookup("#selected");  //prendo questa figura
            model.removeShape(copiedShape);
        }
    }

    public void delete(){
        Node border = drawingWindow.lookup("#selected");
        if (border != null ){
            model.removeShape(border);
        }
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
    
}
