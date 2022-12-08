package drawingSoftware;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Model {

    /*

     * In questo modello vanno tutte le figure che inserisco nella drawingWindow 
     */
    
    private ObservableList<Node> shapes; 
    private final ObjectProperty<Node> currentShape = new SimpleObjectProperty<>(null);

    public Model(){
        this.shapes = FXCollections.observableArrayList();
    }

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

    /*

     * When a new shape has been added, the context is being saved before executing the operation
     */
    public void addShape(Node shape){
        shapes.add(shape);
    }

    public void removeShape(Node node){
        shapes.remove(node);
    }

    public void removeAll(){
        shapes.removeAll(shapes);
    }

    /*
     * usata in undo
     */
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
