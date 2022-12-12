package drawingSoftware.Testing.CommandTest.BackupCommandTest.ShapeCommandTest;

import org.junit.*;

import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.ShapeCommand.MoveCommand;
import drawingSoftware.Managers.MoveManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import static org.junit.Assert.*;

public class MoveCommandTest {

    MoveCommand moveCommand; 
    Pane drawingWindow; 
    Model model; 
    Shape shape; 
    ObservableList<Node> backup; 

    @Before
    public void setUp(){
        model = Model.getInstance();
        drawingWindow = new Pane(); 
    }

    @Test
    public void testExecute(){
        /*

        * Here we're assuring that a MoveCommand istance saves a backup
        * of the model before executing its operation. 
        * 
        * Test cases: 
        * 1) drawing a pentagon
        * 2) drawing an ellipse (representative for Line and Rectangle)
        * 3) drawing a null figure
        */

        /*

        * 1) 
        */
        Polygon pentagon = new Polygon();
        pentagon.getPoints().addAll(new Double[]{
            30.0,10.0,
            10.0,30.0,
            20.0,50.0,
            40.0,50.0,
            50.0,30.0,
        });

        moveCommand = new MoveCommand(drawingWindow, model, new MoveManager());
        backup = FXCollections.observableArrayList(moveCommand.getModel().getAllShapes());
        moveCommand.execute(); 
        assertEquals(moveCommand.getBackup(), backup);

        /*

        * 2)  
        */

        Ellipse ellipse = new Ellipse(10.0,10.0,10.0,10.0); 
        model.addShape(ellipse);
        model.setSelectedShape(ellipse);
        moveCommand = new MoveCommand(drawingWindow, model, new MoveManager());
        backup = FXCollections.observableArrayList(moveCommand.getModel().getAllShapes());
        moveCommand.execute();
        assertEquals(moveCommand.getBackup(), backup);

        /*

        * 3) 
        */

        model.setSelectedShape(null);
        moveCommand = new MoveCommand(drawingWindow, model, new MoveManager());
        backup = FXCollections.observableArrayList(moveCommand.getModel().getAllShapes());
        moveCommand.execute();
        assertEquals(moveCommand.getBackup(), backup);          
    } 
}    


