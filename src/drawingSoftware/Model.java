package drawingSoftware;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Model {

    /*

    * The Model class is responsible for holding
    * the data of the application. In this specific case,
    * the data are made up by:
    * - An observableList of shapes which gets updated
    * by the controller after the user interacts with the view,
    * following the philosophy of the MVC pattern.
    * - An istance of ObjectProperty which tracks the currentShape,
    * which corresponds to the current shape the user selects on the 
    * drawing window of the GUI. 
    * 
    */
    
    private ObservableList<Node> shapes; 
    private final ObjectProperty<Node> currentShape = new SimpleObjectProperty<>(null);
    // model static according to Singleton pattern
    private static Model model;

    private Model(){
        this.shapes = FXCollections.observableArrayList();
    }

    /*

     * Singleton pattern. It must be only one istance at running time.  
     */
    public static Model getInstance(){
        if (model == null){
            model = new Model(); 
        }
        return model; 
    }

    // method for setting the current selected shape 
    public void setSelectedShape(Node node){
        this.currentShape.setValue(node);
    }

    public Node getSelectedShape(){
        return this.currentShape.getValue();
    }

    public final void setCurrentShape(Node node) {
        if (getSelectedShape() != node){
            currentShape.set(node);
        }
    }

    public ObjectProperty<Node> getSelectedProperty(){
        return this.currentShape;
    }

    public ObservableList<Node> getAllShapes(){
        return this.shapes;
    }

    public void addShape(Node shape){
        shapes.add(shape);
    }

    public void removeShape(Node node){
        shapes.remove(node);
    }

    public void removeAll(){
        shapes.removeAll(shapes);
    }


    /* used in undo for restoring a previous state of the model */
    public void addAll(List<Node> list){
        removeAll();   
        shapes.addAll(list);
    }

    /*

     * For testing purposes. 
     */
    public Node getLastShape(){
        return shapes.get(shapes.size()-1);
    }
}
