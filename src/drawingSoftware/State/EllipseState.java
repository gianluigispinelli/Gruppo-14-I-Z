package drawingSoftware.State;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class EllipseState implements State{

    @Override
    public void drawShape(Pane drawableWindow, ColorPicker borderColorPicker, ColorPicker interiorColorPicker,double startDragX, double startDragY, double finalDragX, double finalDragY) {
        
        /* CONTROLLI PER NON DISEGNARE LA FIGURA ESTERNA ALL'AREA DI DISEGNO */
        finalDragX = finalDragX < 0.0 ? 0.0 : finalDragX;
        startDragX = startDragX < 0.0 ? 0.0 : startDragX;
        finalDragY = finalDragY < 0.0 ? 0.0 : finalDragY;
        startDragY = startDragY < 0.0 ? 0.0 : startDragY;

        Ellipse e = new Ellipse();

        /* DEBUGGING FUNCTIONS */
        // System.out.println("start drag x -> " + startDragX);
        // System.out.println("start drag y -> " + startDragY);
        // System.out.println("final drag x -> " + finalDragX);
        // System.out.println("final drag y -> " + finalDragY);

        /* Calcolo delle coordinate del centro in base a quelle del punto dove è
         * iniziato il drag e quello dove è finito. 
         */
        if (startDragX < finalDragX){
            e.setCenterX((startDragX +  Math.abs(finalDragX-startDragX)/2));    
        }
        else{
            e.setCenterX((finalDragX +  Math.abs(finalDragX-startDragX)/2));    
        }

        /* CALCOLO DI WIDTH E HEIGHT */
        if (startDragY < finalDragY){
            e.setCenterY((startDragY +  Math.abs(finalDragY-startDragY)/2));  
        }
        else{
            e.setCenterY((finalDragY +  Math.abs(finalDragY-startDragY)/2));  
        }
        
        /* Setto le proprietà del nodo dopo essermi calcolato width ed height in base ai 
         * valori che ottengo da finalDrag e startDrag su entrambe le coordinate.
         *  -La width è il valore assoluto sulle coordinate x tra il punto dove ho iniziato il drag 
         *   e il punto dove l'utente ha terminato il drag.
         *  -La height è il valore assoluto sulle coordinate y tra il punto dove ho iniziato il drag 
         *   e il punto dove l'utente ha terminato il drag.
         * Width e height sono i valori x e y dell'ellisse.
         * Settiamo questi valori all'ellisse tramite i metodi setRadiusX e setRadiusY.
         */

        double width = (Math.abs(finalDragX - startDragX));
        double height = (Math.abs(finalDragY - startDragY));
        e.setRadiusX(width/2.0);
        e.setRadiusY(height/2.0);

        /* setto i colori in base a quelli selezionati nei colorpicker */
        e.setStroke(borderColorPicker.getValue());
        e.setFill(interiorColorPicker.getValue());     

        /* Funzioni per il debugging */
        // System.out.println("width: " + (finalDragX - startDragX));
        // System.out.println("length: " + (finalDragY - startDragY));
        // System.out.println("Ellipse: " + "(" + e.getRadiusX() + "," + e.getRadiusY() + ")");

        /* L'ellipse in questo caso è un nodo: verrà aggiunto alla fine di questo metodo 
         * come figlio della Pane che è stata passata a questa funzione. La pane in questione
         * è quella chiamata drawingWindow ed è presente nel file FXML "mainInteface".
         */
        e.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                double width = e.getLayoutBounds().getWidth();
                    double height = e.getLayoutBounds().getHeight();
                    double strokewidth = e.getStrokeWidth();
                    double x = e.getCenterX();
                    double y = e.getCenterY();
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
                    Node removeBorder = drawableWindow.lookup("#selected");
                    Node changeId = drawableWindow.lookup("#selectedShape");
                    if (changeId!=null)
                    changeId.setId("");
                    e.setId("selectedShape");
                    drawableWindow.getChildren().remove(removeBorder);
                    drawableWindow.getChildren().add(border);  
            }
        });

        drawableWindow.getChildren().add(e);      
    }
    
    @Override
    public ObservableBooleanValue isNotSegmentState() {
        // TODO Auto-generated method stub
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }
    
}
