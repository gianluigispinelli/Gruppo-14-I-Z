package drawingSoftware;



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

    public Shape getCopiedShape(){
        return this.copiedShape;
    }
    
    public void copy(){
        /* dopo copy non bisogna salvare uno snapshot perché non cambia nulla */
        if (drawingWindow.lookup("#selected")!=null){  // se abbiamo selezionato una figura
            copiedShape = (Shape)drawingWindow.lookup("#selected");  //prendo questa figura
        }
    } 

    public void paste(){
        /*
         * Caso in cui la figura copiata è un rettangolo 
         */
        if (copiedShape instanceof Rectangle){    // se è un rettangolo
            Rectangle rectangle = (Rectangle)copiedShape;
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

            copiedRectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    double width = copiedRectangle.getLayoutBounds().getWidth();
                        double height = copiedRectangle.getLayoutBounds().getHeight();
                        double strokewidth = copiedRectangle.getStrokeWidth();
                        double x = copiedRectangle.getX();
                        double y = copiedRectangle.getY();
                        
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
                        border.setX(x - strokewidth/2.0);
                        border.setY(y - strokewidth/2.0);
                        border.setFill(javafx.scene.paint.Color.TRANSPARENT);
                        border.setStroke(javafx.scene.paint.Color.BLUE);
                        border.getStrokeDashArray().addAll(25d, 10d);
                        
                        // removeOtherBorder();
                        // Node removeBorder = drawingWindow.lookup("#selected");
                        Node changeId = drawingWindow.lookup("#selected");
                        if (changeId!=null)
                        changeId.setId("");
                        copiedRectangle.setId("selected");
                        
                        // drawingWindow.getChildren().remove(removeBorder);
                }
            });
            model.addShape(copiedRectangle);
        }
        /*
         * Caso in cui è una linea
         */
        else if (copiedShape instanceof Line){
            Line line = (Line)copiedShape;
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

            copiedLine.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    double width = copiedLine.getLayoutBounds().getWidth();
                        double height = copiedLine.getLayoutBounds().getHeight();
    
                        double x, y; 
    
                        if (copiedLine.getStartX() < copiedLine.getEndX()){
                            x = copiedLine.getStartX();
                        }else{
                            x = copiedLine.getEndX();
                        }
    
                        if (copiedLine.getStartY() < copiedLine.getEndY()){
                            y = copiedLine.getStartY();  
                        }else{
                            y = copiedLine.getEndY();
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
    
                        
                        // Node removeBorder = drawingWindow.lookup("#selected");
                        Node changeId = drawingWindow.lookup("#selected");
                        if (changeId!=null)
                        changeId.setId("");
                        copiedLine.setId("selected");
                        // drawingWindow.getChildren().remove(removeBorder);
                }
            });

            model.addShape(copiedLine);
        }
        /*
         * Caso in cui è un ellissi
         */
        else{
            Ellipse ellipse = (Ellipse)copiedShape;
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

            copiedEllipse.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    double width = copiedEllipse.getLayoutBounds().getWidth();
                        double height = copiedEllipse.getLayoutBounds().getHeight();
                        double strokewidth = copiedEllipse.getStrokeWidth();
                        double x = copiedEllipse.getCenterX();
                        double y = copiedEllipse.getCenterY();
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
                        border.setX(x - strokewidth/2.0 - width/2);
                        border.setY(y - strokewidth/2.0 - height/2);
                        border.setFill(javafx.scene.paint.Color.TRANSPARENT);
                        border.setStroke(javafx.scene.paint.Color.BLUE);
                        border.getStrokeDashArray().addAll(25d, 10d);
                        
                        // removeOtherBorder();
                        // Node removeBorder = drawingWindow.lookup("#selected");
                        Node changeId = drawingWindow.lookup("#selected");
                        if (changeId!=null)
                        changeId.setId("");
                        copiedEllipse.setId("selected");
                        // drawingWindow.getChildren().remove(removeBorder);
                }
            });
            model.addShape(copiedEllipse);
        }
    }

    public void cut(){
        if (drawingWindow.lookup("#selected")!=null){  // se abbiamo selezionato una figura
            copiedShape = (Shape)drawingWindow.lookup("#selected");  //prendo questa figura
            model.removeShape(copiedShape);
        }
    }
    
}
