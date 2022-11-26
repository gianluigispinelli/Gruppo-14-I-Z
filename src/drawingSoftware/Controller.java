package drawingSoftware;

import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.GraphicsContext;

public class Controller implements Initializable{

    @FXML
    private AnchorPane ancorPane;

    @FXML
    private ColorPicker borderColorPicker;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label chooseInteriorColorLabel;

    @FXML
    private Pane paneDrawableWindow;
    
    @FXML
    private Canvas drawingWindow;

    @FXML
    private Button ellipseButton;

    @FXML
    private Menu fileMenu;

    @FXML
    private ColorPicker interiorColorPicker;

    @FXML
    private MenuItem loadItem;

    @FXML
    private Button rectangleButton;

    @FXML
    private MenuItem saveItem;

    @FXML
    private Button segmentButton;


    private double startDragX;
    private double startDragY;
    private GraphicsContext gc;

    private SelectedFigure selectedFigure; 

    /*
     * stroke = contorno
     * fill = contenuto 
     * task: Function for selecting a geometrical shape in the ribbon amongst the following list: [rectangle, line segment, ellipse]
     * task: Function for adding the selected shape with fixed size from the ribbon in the drawable window
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedFigure = new SelectedFigure(new SegmentState());
        
        gc = drawingWindow.getGraphicsContext2D();
        
        borderColorPicker.setValue(Color.BLACK);
        interiorColorPicker.setValue(Color.WHITE);
        
        //this event because user must know where he can draw.
        drawingWindow.setOnMouseExited(e->{
            drawingWindow.setCursor(Cursor.CROSSHAIR);
        });
        drawingWindow.setOnMousePressed(e -> {
            if(e.getButton() == MouseButton.PRIMARY){
            startDragX = e.getX();
            startDragY = e.getY();
            }
        });
        //event filter because mouse move quickly ad 
        drawingWindow.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if(e.getButton() == MouseButton.PRIMARY){
                double finalDragX = e.getX();
                double finalDragY = e.getY();
                selectedFigure.drawShape(borderColorPicker, interiorColorPicker, gc, startDragX, startDragY, finalDragX, finalDragY);
            }
        }
        });
    }
 
    @FXML
    void onEllipseClick(ActionEvent event) {
        
        selectedFigure.changeState(new EllipseState());
    }

    @FXML
    void onLineClick(ActionEvent event) {
        selectedFigure.changeState(new SegmentState());
    }

    @FXML
    void onRectangleClick(ActionEvent event) {
        
        selectedFigure.changeState(new RectangleState());
    }

    @FXML
    void colorBorder(ActionEvent event) {

    }

    @FXML
    void colorInterior(ActionEvent event) {

    }

    @FXML
    void onLoad(ActionEvent event) {

    }

    @FXML
    void onSave(ActionEvent event) {
    } 

}


