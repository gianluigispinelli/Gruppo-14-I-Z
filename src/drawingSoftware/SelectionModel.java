package drawingSoftware;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.Node;

/* Classe Model in cui andranno i nodi selezionati */

public class SelectionModel {

    Set<Node> selection = new HashSet<>();
    
    public void add(Node node) {
        node.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 10, 0, 0);");
        selection.add(node);
    }

    public void remove(Node node) {
        node.setStyle("-fx-effect: null");
        selection.remove(node);
    }

    public void log() {
        System.out.println( "Items in model: " + Arrays.asList( selection.toArray()));
    }
}
