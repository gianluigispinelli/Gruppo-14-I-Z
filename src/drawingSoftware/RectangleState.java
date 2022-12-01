package drawingSoftware;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class RectangleState implements State{

    private double startX;
    private double startY; 
    private Rectangle r;

    @Override
    public void drawShape(Pane drawableWindow, ColorPicker borderColorPicker, ColorPicker interiorColorPicker,double startDragX, double startDragY, double finalDragX, double finalDragY) {
        
        /* CONTROLLI PER NON DISEGNARE LA FIGURA ESTERNA ALL'AREA DI DISEGNO */
        finalDragX = finalDragX < 0.0 ? 0.0 : finalDragX;
        startDragX = startDragX < 0.0 ? 0.0 : startDragX;
        finalDragY = finalDragY < 0.0 ? 0.0 : finalDragY;
        startDragY = startDragY < 0.0 ? 0.0 : startDragY;

        r = new Rectangle();


        /* DEBUGGING FUNCTIONS */
        // System.out.println("start drag x -> " + startDragX);
        // System.out.println("start drag y -> " + startDragY);
        // System.out.println("final drag x -> " + finalDragX);
        // System.out.println("final drag y -> " + finalDragY);

        /* Calcolo delle coordinate del centro in base a quelle del punto dove �
         * iniziato il drag e quello dove � finito. 
         */

    
        if (startDragX < finalDragX){
            this.r.setX(startDragX);    
        }
        else{
            this.r.setX(finalDragX);    
        }

        /* CALCOLO DI WIDTH E HEIGHT */
        if (startDragY < finalDragY){
            this.r.setY(startDragY);  
        }
        else{
            this.r.setY(finalDragY);  
        }
        
        /* Setto le propriet� del nodo dopo essermi calcolato width ed height in base ai 
         * valori che ottengo da finalDrag e startDrag su entrambe le coordinate.
         *  -La width � il valore assoluto sulle coordinate x tra il punto dove ho iniziato il drag 
         *   e il punto dove l'utente ha terminato il drag.
         *  -La height � il valore assoluto sulle coordinate y tra il punto dove ho iniziato il drag 
         *   e il punto dove l'utente ha terminato il drag.
         * Width e height sono i valori x e y dell'ellisse.
         * Settiamo questi valori all'ellisse tramite i metodi setRadiusX e setRadiusY.
         */

        double width = (Math.abs(finalDragX - startDragX));
        double height = (Math.abs(finalDragY - startDragY));
        this.r.setWidth(width);
        this.r.setHeight(height);

        /* setto i colori in base a quelli selezionati nei colorpicker */
        this.r.setStroke(borderColorPicker.getValue());
        this.r.setFill(interiorColorPicker.getValue());     
        
        /* Funzioni per il debugging */
        // System.out.println("width: " + Math.abs(finalDragX - startDragX));
        // System.out.println("length: " + Math.abs(finalDragY - startDragY));
        // System.out.println("Rectangle: " + "(" + r.getX() + "," + r.getY() + ")");

        /* L'ellipse in questo caso � un nodo: verr� aggiunto alla fine di questo metodo 
         * come figlio della Pane che � stata passata a questa funzione. La pane in questione
         * � quella chiamata drawingWindow ed � presente nel file FXML "mainInteface".
         * 
         */

        this.addEventFilter(drawableWindow);

        drawableWindow.getChildren().add(this.r);
        
        this.addMoveListner(drawableWindow);
    }

    

    @Override
    public ObservableBooleanValue isNotSegmentState() {
        // TODO Auto-generated method stub
        ObservableBooleanValue visible = new SimpleBooleanProperty(true);
        return visible;
    }

    private void addEventFilter(Pane drawableWindow){

        this.r.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setSelectedBorder(drawableWindow);
            }
        });

    }

    private void setSelectedBorder(Pane drawableWindow){
        double width = this.r.getLayoutBounds().getWidth();
        double height = this.r.getLayoutBounds().getHeight();
        double strokewidth = this.r.getStrokeWidth();
        double x = this.r.getX();
        double y = this.r.getY();


        /*
        * border: questo rettangolo evidenzia la figura selezionata
        * vengono prese anche le dimensioni del bordo, nel caso in cui la figura ha un bordo pi� spesso.
        * (x,y) del border sono le coordinate del punto in alto a sinistra.
        * per fare in modo che comprende anche il bordo pi� spesso viene sottratta alle coord (x,y)
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
        border.translateXProperty().bind(this.r.translateXProperty());
        border.translateYProperty().bind(this.r.translateYProperty());
        // removeOtherBorder();
        Node removeBorder = this.removeDeselectedBorder(drawableWindow);


        drawableWindow.getChildren().remove(removeBorder);
        drawableWindow.getChildren().add(border); 

    }
    private Node removeDeselectedBorder(Pane drawableWindow){
        // removeOtherBorder();
        Node removeBorder = drawableWindow.lookup("#selected");
        Node changeId = drawableWindow.lookup("#selectedShape");
        if (changeId!=null)
        changeId.setId("");
        this.r.setId("selectedShape");
        return removeBorder;
    }

    private void addMoveListner(Pane drawableWindow){
        this.r.setOnMousePressed(e -> {

            startX = e.getX();
            startY = e.getY();


        });

        this.r.setOnMouseDragged(e -> {

            this.r.setTranslateX(r.getTranslateX() + e.getX() - startX);
            this.r.setTranslateY(r.getTranslateY()+ e.getY() - startY );



        });

    }
    
}