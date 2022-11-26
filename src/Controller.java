package hellofx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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

    @FXML
    void colorBorder(ActionEvent event) {
        //function linked to the color picker that color the border of a shape
    }

    @FXML
    void colorInterior(ActionEvent event) {
        //function linked to the color picker that color the interior of a shape
    }

    @FXML
    void onLoad(ActionEvent event) {

    }

    @FXML
    void onSave(ActionEvent event) {

    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext gc=drawingWindow.getGraphicsContext2D();

        drawingWindow.setOnMouseDragged(e->{
            double x=e.getX()- 5; //5=size(la size sta nel fill React)/2
            double y=e.getY()-5;  //stessa cosa di sopra

            gc.setFill(interiorColorPicker.getValue());
            gc.fillRect(x,y,10,10);
        });
        
    }

}
