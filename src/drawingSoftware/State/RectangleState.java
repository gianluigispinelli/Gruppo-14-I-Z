package drawingSoftware.State;

import drawingSoftware.Model;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class RectangleState implements State{

    

    @Override
    public void drawShape(Model model, Pane drawableWindow, ColorPicker borderColorPicker, ColorPicker interiorColorPicker,double startDragX, double startDragY, double finalDragX, double finalDragY) {
        
        /* CONTROLLI PER NON DISEGNARE LA FIGURA ESTERNA ALL'AREA DI DISEGNO */
        finalDragX = finalDragX < 0.0 ? 0.0 : finalDragX;
        startDragX = startDragX < 0.0 ? 0.0 : startDragX;
        finalDragY = finalDragY < 0.0 ? 0.0 : finalDragY;
        startDragY = startDragY < 0.0 ? 0.0 : startDragY;

        Rectangle r = new Rectangle();

        /* DEBUGGING FUNCTIONS */
        // System.out.println("start drag x -> " + startDragX);
        // System.out.println("start drag y -> " + startDragY);
        // System.out.println("final drag x -> " + finalDragX);
        // System.out.println("final drag y -> " + finalDragY);

        /* Calcolo delle coordinate del centro in base a quelle del punto dove è
         * iniziato il drag e quello dove è finito. 
         */

    
        if (startDragX < finalDragX){
            r.setX(startDragX);    
        }
        else{
            r.setX(finalDragX);    
        }

        /* CALCOLO DI WIDTH E HEIGHT */
        if (startDragY < finalDragY){
            r.setY(startDragY);  
        }
        else{
            r.setY(finalDragY);  
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
        r.setWidth(width);
        r.setHeight(height);

        /* setto i colori in base a quelli selezionati nei colorpicker */
        r.setStroke(borderColorPicker.getValue());
        r.setFill(interiorColorPicker.getValue());     
        
        /* Funzioni per il debugging */
        // System.out.println("width: " + Math.abs(finalDragX - startDragX));
        // System.out.println("length: " + Math.abs(finalDragY - startDragY));
        // System.out.println("Rectangle: " + "(" + r.getX() + "," + r.getY() + ")");

        /* L'ellipse in questo caso è un nodo: verrà aggiunto alla fine di questo metodo 
         * come figlio della Pane che è stata passata a questa funzione. La pane in questione
         * è quella chiamata drawingWindow ed è presente nel file FXML "mainInteface".
         * 
         */
        r.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                double width = r.getLayoutBounds().getWidth();
                    double height = r.getLayoutBounds().getHeight();
                    double strokewidth = r.getStrokeWidth();
                    double x = r.getX();
                    double y = r.getY();
                    
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
                    Node removeBorder = drawableWindow.lookup("#selected");
                    Node changeId = drawableWindow.lookup("#selectedShape");
                    if (changeId!=null)
                    changeId.setId("");
                    r.setId("selectedShape");
                    
                    drawableWindow.getChildren().remove(removeBorder);
                    drawableWindow.getChildren().add(border);  
            }
        });
        model.addShape(r);
    }
    
    @Override
    public ObservableBooleanValue isNotSegmentState() {
        // TODO Auto-generated method stub
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }

    @Override
    public void useTool() {
        // TODO Auto-generated method stub
        
    }
    
}
