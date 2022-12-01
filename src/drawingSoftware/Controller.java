package drawingSoftware;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.SourceDataLine;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Controller implements Initializable{

    @FXML
    private MenuItem cutItem;

    @FXML
    private MenuItem copyItem;

    @FXML
    private MenuItem pasteItem;

    @FXML
    private Button deleteBtn;

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
    private Pane drawingWindow;

    @FXML
    private ScrollPane scrollPane;

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
    private Button selectButton;

    @FXML
    private MenuItem undoItem;

    private double startDragX;
    private double startDragY;

    private SelectedFigure selectedFigure; 

    /*FILE CHOOSER */
    FileChooser filechooser = new FileChooser();
    Receiver receiver = new Receiver();
    FileInvoker fileInvoker = new FileInvoker();    

    /*
     * stroke = contorno
     * fill = contenuto 
     * task: Function for selecting a geometrical shape in the ribbon amongst the following list: [rectangle, line segment, ellipse]
     * task: Function for adding the selected shape with fixed size from the ribbon in the drawable window
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        scrollPane.setContent(drawingWindow);

        /* INIZIALIZZAZIONE VARIABILI PER CARICAMENTO FILE */
        // fc.setInitialDirectory(new File("/home/gianluigi/VSC Workspace/JavaProjects/ProgettoSE/Progetto/src/project"));
        filechooser.setInitialDirectory(new File("."));  // apro cartella corrente
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images", "*.png")); // filechooser mi mostra solo png

        selectedFigure = new SelectedFigure(new SegmentState());
        
        borderColorPicker.setValue(Color.BLACK);
        interiorColorPicker.setValue(Color.WHITE);
        
        //this event because user must know where he can draw.
        // drawingWindow.setOnMouseExited(e->{
        //     drawingWindow.setCursor(Cursor.CROSSHAIR);
        // });

        drawingWindow.setOnMouseEntered(e->{
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
                selectedFigure.drawShape(drawingWindow,borderColorPicker, interiorColorPicker,startDragX, startDragY, finalDragX, finalDragY);
            }
        }
        });
        setInteriorColorPickerVisible();

        /*
         * UNDO FUNCTION
         */
        undoItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        copyItem.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        pasteItem.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
        cutItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        loadItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
    }

    /*
     * Da fare il refactoring con COMMAND
     * @Da inserire nel receiver
     */

    Shape copiedShape; 

    @FXML
    void copyAShape(ActionEvent event) {
        if (drawingWindow.lookup("#selectedShape")!=null){  // se abbiamo selezionato una figura
            copiedShape = (Shape)drawingWindow.lookup("#selectedShape");  //prendo questa figura
        }
    }

    @FXML
    void cutAShape(ActionEvent event) {
        if (drawingWindow.lookup("#selectedShape")!=null){  // se abbiamo selezionato una figura
            copiedShape = (Shape)drawingWindow.lookup("#selectedShape");  //prendo questa figura
            drawingWindow.getChildren().remove(drawingWindow.lookup("#selectedShape"));
        }
    }

    /*
     * Da fare il refactoring con COMMAND
     * @Da inserire nel receiver
     */

    @FXML
    void pasteAShape(ActionEvent event) {
        /*
         * Caso in cui la figura copiata è un rettangolo 
         */
        if (copiedShape instanceof Rectangle){    // se è un rettangolo
            Rectangle rectangle = (Rectangle)copiedShape;
            Double x = rectangle.getX();
            Double y = rectangle.getY();
            Double width = rectangle.getWidth();
            Double height = rectangle.getHeight();
            Paint stroke = rectangle.getStroke();
            Paint fill = rectangle.getFill();
            
            Rectangle copiedRectangle = new Rectangle();
            copiedRectangle.setX(x+10);
            copiedRectangle.setY(y+10);
            copiedRectangle.setWidth(width);
            copiedRectangle.setHeight(height);
            copiedRectangle.setStroke(stroke);
            copiedRectangle.setFill(fill);

            copiedRectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    double width = copiedRectangle.getLayoutBounds().getWidth();
                        double height = copiedRectangle.getLayoutBounds().getHeight();
                        double strokewidth = copiedRectangle.getStrokeWidth();
                        double x = copiedRectangle.getX();
                        double y = copiedRectangle.getY();
                        
                        /*
                         * border: questo rettangolo evidenzia la figura selezionata
                         * vengono prese anche le dimensioni del bordo, nel caso in cui la figura ha un bordo più spesso.
                         * (x,y) del border sono le coordinate del punto in alto a sinistra.
                         * per fare in modo che comprende anche il bordo più spesso viene sottratta alle coord (x,y)
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
                        border.getStrokeDashArray().addAll(25d, 10d);
                        
                        // removeOtherBorder();
                        Node removeBorder = drawingWindow.lookup("#selected");
                        Node changeId = drawingWindow.lookup("#selectedShape");
                        if (changeId!=null)
                        changeId.setId("");
                        copiedRectangle.setId("selectedShape");
                        
                        drawingWindow.getChildren().remove(removeBorder);
                        drawingWindow.getChildren().add(border);  
                }
            });


            drawingWindow.getChildren().add(copiedRectangle);
        }
        /*
         * Caso in cui è una linea
         */
        else if (copiedShape instanceof Line){
            Line line = (Line)copiedShape;
            Double startX = line.getStartX();
            Double startY = line.getStartY();
            Double endX = line.getEndX();
            Double endY = line.getEndY();
            Paint stroke = line.getStroke();

            Line copiedLine = new Line();
            copiedLine.setStartX(startX+10);
            copiedLine.setStartY(startY+10);
            copiedLine.setEndX(endX+10);
            copiedLine.setEndY(endY+10);
            copiedLine.setStroke(stroke);

            copiedLine.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    double width = copiedLine.getLayoutBounds().getWidth();
                        double height = copiedLine.getLayoutBounds().getHeight();
    
                        double x, y; 
    
                        if (copiedLine.getStartX() < copiedLine.getEndX()){
                            x = copiedLine.getStartX();
                        }else{
                            x = copiedLine.getEndX();
                        }
    
                        if (copiedLine.getStartY() < copiedLine.getEndY()){
                            y = copiedLine.getStartY();  
                        }else{
                            y = copiedLine.getEndY();
                        }
                        
                        /*
                         * border: questo rettangolo evidenzia la figura selezionata
                         * vengono prese anche le dimensioni del bordo, nel caso in cui la figura ha un bordo più spesso.
                         * (x,y) del border sono le coordinate del punto in alto a sinistra.
                         * per fare in modo che comprende anche il bordo più spesso viene sottratta alle coord (x,y)
                         * la dimensione della strokewidth della figura selezionata e divide per 2. 
                         */
    
                        Rectangle border = new Rectangle();
                        border.setId("selected");
                        border.setWidth(width);
                        border.setHeight(height);
                        border.setX(x);
                        border.setY(y);
                        border.setFill(javafx.scene.paint.Color.TRANSPARENT);
                        border.setStroke(javafx.scene.paint.Color.BLUE);
                        border.getStrokeDashArray().addAll(25d, 10d);
    
                        
                        Node removeBorder = drawingWindow.lookup("#selected");
                        Node changeId = drawingWindow.lookup("#selectedShape");
                        if (changeId!=null)
                        changeId.setId("");
                        copiedLine.setId("selectedShape");
                        drawingWindow.getChildren().remove(removeBorder);
                        drawingWindow.getChildren().add(border);
                }
            });

            drawingWindow.getChildren().add(copiedLine);
        }
        /*
         * Caso in cui è un ellissi
         */
        else{
            Ellipse ellipse = (Ellipse)copiedShape;
            Double x = ellipse.getCenterX();
            Double y = ellipse.getCenterY();
            Double width = ellipse.getRadiusX();
            Double height = ellipse.getRadiusY();
            Paint stroke = ellipse.getStroke();
            Paint fill = ellipse.getFill();
            
            Ellipse copiedEllipse = new Ellipse();
            copiedEllipse.setCenterX(x+10);
            copiedEllipse.setCenterY(y+10);
            copiedEllipse.setRadiusX(width);
            copiedEllipse.setRadiusY(height);
            copiedEllipse.setStroke(stroke);
            copiedEllipse.setFill(fill);

            copiedEllipse.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    double width = copiedEllipse.getLayoutBounds().getWidth();
                        double height = copiedEllipse.getLayoutBounds().getHeight();
                        double strokewidth = copiedEllipse.getStrokeWidth();
                        double x = copiedEllipse.getCenterX();
                        double y = copiedEllipse.getCenterY();
                        /*
                         * border: questo rettangolo evidenzia la figura selezionata
                         * vengono prese anche le dimensioni del bordo, nel caso in cui la figura ha un bordo più spesso.
                         * (x,y) del border sono le coordinate del punto in alto a sinistra.
                         * per fare in modo che comprende anche il bordo più spesso viene sottratta alle coord (x,y)
                         * la dimensione della strokewidth della figura selezionata e divide per 2. 
                         */
    
                        Rectangle border = new Rectangle();
                        border.setId("selected");
                        border.setWidth(width);
                        border.setHeight(height);
                        border.setX(x - strokewidth/2.0 - width/2);
                        border.setY(y - strokewidth/2.0 - height/2);
                        border.setFill(javafx.scene.paint.Color.TRANSPARENT);
                        border.setStroke(javafx.scene.paint.Color.BLUE);
                        border.getStrokeDashArray().addAll(25d, 10d);
                        
                        // removeOtherBorder();
                        Node removeBorder = drawingWindow.lookup("#selected");
                        Node changeId = drawingWindow.lookup("#selectedShape");
                        if (changeId!=null)
                        changeId.setId("");
                        copiedEllipse.setId("selectedShape");
                        drawingWindow.getChildren().remove(removeBorder);
                        drawingWindow.getChildren().add(border);  
                }
            });
    
            drawingWindow.getChildren().add(copiedEllipse);    
        }
    }

    
 
    @FXML
    void onEllipseClick(ActionEvent event) {
        selectedFigure.changeState(new EllipseState());
        setInteriorColorPickerVisible();
    }

    @FXML
    void onLineClick(ActionEvent event) {
        selectedFigure.changeState(new SegmentState());
        setInteriorColorPickerVisible();
    }

    @FXML
    void onRectangleClick(ActionEvent event) {
        
        selectedFigure.changeState(new RectangleState());
        setInteriorColorPickerVisible();
    }

    @FXML
    void colorBorder(ActionEvent event) {

    }

    @FXML
    void colorInterior(ActionEvent event) {

    }

    @FXML
    void onLoad(ActionEvent event) {
        Command command = new LoadCommand(receiver, filechooser, drawingWindow);
        fileInvoker.setCommand(command);
        fileInvoker.executeCommand();
    }

    @FXML
    void onSave(ActionEvent event) {
        SaveCommand saveCommand = new SaveCommand(this.receiver,drawingWindow);
        this.fileInvoker.setCommand(saveCommand);
        this.fileInvoker.executeCommand();
    } 

    @FXML
    void deleteShape(ActionEvent event){
        Node border = drawingWindow.lookup("#selected");
        Node shape = drawingWindow.lookup("#selectedShape");

        if (border != null && shape!= null){
            drawingWindow.getChildren().remove(border);
            drawingWindow.getChildren().remove(shape);
        }
    }

    @FXML
    void select(ActionEvent event) {
        selectedFigure.changeState(new SelectState());
    }

    @FXML
    void undo(ActionEvent event){
        int n_elements = drawingWindow.getChildren().size();
        if (n_elements>0)
        drawingWindow.getChildren().remove(n_elements-1);
    }

    private void setInteriorColorPickerVisible(){
        interiorColorPicker.visibleProperty().bind(selectedFigure.getSegmentState());
        chooseInteriorColorLabel.visibleProperty().bind(selectedFigure.getSegmentState());
    }
}


