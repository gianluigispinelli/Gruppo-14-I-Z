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
import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

import drawingSoftware.Editor.CopyCommand;
import drawingSoftware.Editor.CutCommand;
import drawingSoftware.Editor.Editor;
import drawingSoftware.Editor.EditorInvoker;
import drawingSoftware.Editor.PasteCommand;
import drawingSoftware.Load.Save.Command;
import drawingSoftware.Load.Save.FileInvoker;
import drawingSoftware.Load.Save.LoadCommand;
import drawingSoftware.Load.Save.Receiver;
import drawingSoftware.Load.Save.SaveCommand;
import drawingSoftware.State.EllipseState;
import drawingSoftware.State.RectangleState;
import drawingSoftware.State.SegmentState;
import drawingSoftware.State.SelectState;
import drawingSoftware.State.SelectedFigure;
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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
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
     * Istanza del receiver Editor e dell'EditorInvoker
     */
    Editor editor = new Editor();
    EditorInvoker editorInvoker = new EditorInvoker();

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

    @FXML
    void copyAShape(ActionEvent event) {
        // CopyCommand copycommand = new CopyCommand(editor, drawingWindow);
        // editorInvoker.setCommand(copycommand);
        // editorInvoker.executeCommand();
        System.out.println("copia");
        Clipboard cp = Clipboard.getSystemClipboard();
        ClipboardContent cpCnt = new ClipboardContent();
        cpCnt.put(null, cpCnt);
        cp.setContent(cpCnt);
    }

    @FXML
    void cutAShape(ActionEvent event) {
        // CutCommand cutCommand = new CutCommand(editor, drawingWindow);
        // editorInvoker.setCommand(cutCommand);
        // editorInvoker.executeCommand();
        System.out.println("cut");
    }

    @FXML
    void pasteAShape(ActionEvent event) {
        // PasteCommand pasteCommand = new PasteCommand(editor, drawingWindow);
        // editorInvoker.setCommand(pasteCommand);
        // editorInvoker.executeCommand();
        System.out.println("paste");
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


