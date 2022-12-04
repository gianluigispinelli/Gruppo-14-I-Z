package drawingSoftware;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.event.ChangeListener;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Model {

    /*

     * In questo modello vanno tutte le figure che inserisco nella drawingWindow 
     */
    
    ObservableList<Node> shapes; 

    public Model(){
        this.shapes = FXCollections.observableArrayList();
    }

    public ObservableList<Node> getAllShapes(){
        return this.shapes;
    }

    public void addShape(Node shape){
        shapes.add(shape);
    }

    public Node getShape(int index){
        return shapes.get(index);
    }

    public void removeShape(Node node){
        shapes.remove(node);
    }

    public void removeLast(){
        shapes.remove(shapes.size()-1);
    }

    public void removeAll(){
        for (Node node : shapes) {
            removeShape(node);
        }
    }

    public void addAll(List<Node> list){
        for (Node node : shapes) {
            shapes.remove(node);
        }    
        shapes.addAll(list);
    }

    public int getSize(){
        return shapes.size(); 
    }
}
