package drawingSoftware;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class RectangleState implements State{

    @Override
    public void drawShape(Pane drawableWindow, ColorPicker borderColorPicker, ColorPicker interiorColorPicker,double startDragX, double startDragY, double finalDragX, double finalDragY) {
        
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
         */
        drawableWindow.getChildren().add(r);      
    }
    
    @Override
    public ObservableBooleanValue isNotSegmentState() {
        // TODO Auto-generated method stub
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }
    
}
