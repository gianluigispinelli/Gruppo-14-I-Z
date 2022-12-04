package drawingSoftware;

import java.util.List;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Model {

    /*

     * In questo modello vanno tutte le figure che inserisco nella drawingWindow 
     */
    
    ObservableList<Node> shapes; 
    Stack<List<Node>> history;


    public Model(){
        this.shapes = FXCollections.observableArrayList();
        this.history = new Stack<List<Node>>();
    }

    public ObservableList<Node> getAllShapes(){
        return this.shapes;
    }

    /*

     * Quando viene aggiunta una nuova shape, viene salvato il contesto prima di effettuare l'operazione
     */
    public void addShape(Node shape){
        this.history.push(FXCollections.observableArrayList(shapes));   /* passaggio per valore e non riferimento */
        shapes.add(shape);
    }

    public Node getShape(int index){
        return shapes.get(index);
    }

    public void removeShape(Node node){
        this.history.push(FXCollections.observableArrayList(shapes));   /* passaggio per valore e non riferimento */
        shapes.remove(node);
    }

    public void removeLast(){
        shapes.remove(shapes.size()-1);
    }

    public void removeAll(){
        shapes.removeAll(shapes);
    }

    public void addAll(List<Node> list){
        removeAll();   
        shapes.addAll(list);
    }

    /*

     * backup functions
     */

    public void saveBackup(){
        this.history.push(FXCollections.observableArrayList(shapes));
    }

    public void backup(){   /* restore a previous context */
        if (!history.empty()){
            this.removeAll();
            this.addAll(this.history.pop());
        }
        
    }

    public int getSize(){
        return shapes.size(); 
    }
}
