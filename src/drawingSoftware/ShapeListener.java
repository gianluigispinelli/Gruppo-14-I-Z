package drawingSoftware;

import drawingSoftware.Managers.ResizeTextFieldManager;
import drawingSoftware.Shapes.MyBoundingBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/*

 * ShapeListener is a class responsible for the business logic
 * regarding adding listeners to the changes applied
 * to the nodes which take part of the Model class.  
 */
public class ShapeListener {
    private Model model;
    private MyBoundingBox boundingBox;
    private Pane drawingWindow;
    private ResizeTextFieldManager textFieldManager;
    

    // constructor
    public ShapeListener(Model model, MyBoundingBox boundingBox, Pane drawingWindow, ResizeTextFieldManager textFieldManager) {
        this.model = model;
        this.boundingBox = boundingBox;
        this.drawingWindow = drawingWindow;
        this.textFieldManager = textFieldManager;
    }

    /*

     * When the Controller is inizialized, we're adding a listener to all the shapes of the model.
     * When the list of shapes belonging to the Model is being changed, the Controller reflects
     * these change to the View, specifically to the drawingWindow.  
     */
    public void addListenerToShapes(){
        model.getAllShapes().addListener(new ListChangeListener<Node>() {

            @Override
            public void onChanged(Change<? extends Node> change) {        /* If the drawing window is modified */
                while(change.next()){
                    for (Node node : change.getRemoved()) {         /* for the nodes that are removed from the drawingWindow */
                        drawingWindow.getChildren().remove(node);       /* removing the node from the child of the drawingWindow */
                    }
                    for (Node node : change.getAddedSubList()){         /* for the nodes that are added to the drawingWindow */
                        drawingWindow.getChildren().add(node);      /* adding the node to the drawingWindow */
                    }
                }
            }
        });

       
    }
    /*

     * When the Controller is inizialized, we're adding a listener to the selected shape.
     * When the selected shape of the Model is being changed, the Controller reflects
     * these change to the View, specifically to the drawingWindow.  
     */
    public void addListenerToSelectedShape(){
        model.getSelectedProperty().addListener(new ChangeListener<Node>() {
            /*
            * This code if for showing the selection border of the selected figure
            */
            @Override
            public void changed(ObservableValue<? extends Node> arg0, Node oldShapeSelected, Node newShapeSelected) {
                if (boundingBox != null){       /* removing the old bounding box */
                    drawingWindow.getChildren().remove(boundingBox);
                    textFieldManager.setDisableFieldWidth(true);
                    textFieldManager.setDisableFieldHeight(true);
                }
                if (newShapeSelected != null){                       
                        Bounds bounds = newShapeSelected.getBoundsInLocal(); /* taking the bounds of the selected figure */
                        boundingBox = new MyBoundingBox(
                            bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight()
                            );
                        /*

                         * adding a boundingbox over the selected figure to visually
                         * notify the user he has selected the shape 
                         */
                        drawingWindow.getChildren().add(boundingBox);   
                        boundingBox.layoutYProperty().bind(newShapeSelected.layoutYProperty());
                        boundingBox.layoutXProperty().bind(newShapeSelected.layoutXProperty());
                        
                        textFieldManager.setDisableFieldWidth(false);
                        textFieldManager.setDisableFieldHeight(false);
                        textFieldManager.checkShapeSelected(newShapeSelected);
                }
            }
        });

       
    }
    public MyBoundingBox getBoundingBox(){
        return this.boundingBox;
    }
    
}
