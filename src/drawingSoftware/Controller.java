package drawingSoftware;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import drawingSoftware.Editor.Command;
import drawingSoftware.Editor.CopyCommand;
import drawingSoftware.Editor.CutCommand;
import drawingSoftware.Editor.DeleteCommand;
import drawingSoftware.Editor.Editor;
import drawingSoftware.Editor.EditorInvoker;
import drawingSoftware.Editor.LoadCommand;
import drawingSoftware.Editor.PasteCommand;
import drawingSoftware.Editor.SaveCommand;
import drawingSoftware.Editor.UndoCommand;
import drawingSoftware.Tool.EllipseTool;
import drawingSoftware.Tool.LineTool;
import drawingSoftware.Tool.RectangleTool;
import drawingSoftware.Tool.SelectTool;
import drawingSoftware.Tool.SelectedToolContext;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    private SelectedToolContext selectedFigure; 

    /*FILE CHOOSER */
    FileChooser filechooser = new FileChooser();
    Receiver receiver = new Receiver();
    FileInvoker fileInvoker = new FileInvoker();    

    /*
     * Istanza del receiver Editor e dell'EditorInvoker
     */
    Editor editor; 
    EditorInvoker editorInvoker;
    Stack<ObservableList<Node>> history;
    Model model; 

    /*
     * stroke = contorno
     * fill = contenuto 
     * task: Function for selecting a geometrical shape in the ribbon amongst the following list: [rectangle, line segment, ellipse]
     * task: Function for adding the selected shape with fixed size from the ribbon in the drawable window
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        scrollPane.setContent(drawingWindow);
        this.setCursorCrosschair();

        model = new Model();          /* Model dove mi salvo le shapes che aggiungo */
        history = new Stack<ObservableList<Node>>();

        /*
         * Il controller fa da intermediario tra il model delle figure aggiunte e cosa succede nella view
         */
        model.shapes.addListener(new ListChangeListener<Node>() {

            @Override
            public void onChanged(Change<? extends Node> arg0) {        /* se modifichiamo la drawingWindow */
                drawingWindow.getChildren().clear();
                drawingWindow.getChildren().addAll(model.shapes);
            }
        });

        /*

         * TODO: le figure selezionate dovranno andare in un model e non devono avere un'etichetta
         */

        editor = new Editor(model, drawingWindow);
        
        editorInvoker = new EditorInvoker();
        scrollPane.setContent(drawingWindow);

        /* INIZIALIZZAZIONE VARIABILI PER CARICAMENTO FILE */
        // fc.setInitialDirectory(new File("/home/gianluigi/VSC Workspace/JavaProjects/ProgettoSE/Progetto/src/project"));
        filechooser.setInitialDirectory(new File("."));  // apro cartella corrente
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images", "*.png")); // filechooser mi mostra solo png
        
        borderColorPicker.setValue(Color.BLACK);
        interiorColorPicker.setValue(Color.WHITE);

        selectedFigure = new SelectedToolContext(new RectangleTool(model, drawingWindow), drawingWindow, borderColorPicker, interiorColorPicker);

        setInteriorColorPickerVisible();

        /*
         * Accelerators
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
        CopyCommand copycommand = new CopyCommand(editor);
        editorInvoker.setCommand(copycommand);
        editorInvoker.executeCommand();
    }

    @FXML
    void cutAShape(ActionEvent event) {
        CutCommand cutCommand = new CutCommand(editor);
        // careTaker.makeBackup();
        editorInvoker.setCommand(cutCommand);
        editorInvoker.executeCommand();
    }

    @FXML
    void pasteAShape(ActionEvent event) {
        PasteCommand pasteCommand = new PasteCommand(editor);
        // careTaker.makeBackup();
        editorInvoker.setCommand(pasteCommand);
        editorInvoker.executeCommand();
    }    
 
    @FXML
    void onEllipseClick(ActionEvent event) {
        
        selectedFigure.changeTool(new EllipseTool(model, drawingWindow));
        setInteriorColorPickerVisible();
    }

    @FXML
    void onLineClick(ActionEvent event) {
        selectedFigure.changeTool(new LineTool(model, drawingWindow));
        setInteriorColorPickerVisible();
    }

    @FXML
    void onRectangleClick(ActionEvent event) {
        
        selectedFigure.changeTool(new RectangleTool(model, drawingWindow));
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
        DeleteCommand deleteCommand =  new DeleteCommand(model, drawingWindow);
        this.fileInvoker.setCommand(deleteCommand);
        this.fileInvoker.executeCommand();
    }

    @FXML
    void select(ActionEvent event) {
        selectedFigure.changeTool(new SelectTool(model, drawingWindow));
        setInteriorColorPickerVisible();
    }

    @FXML
    void undo(ActionEvent event){
        Command undoCommand = new UndoCommand(model);
        editorInvoker.setCommand(undoCommand);
        editorInvoker.executeCommand();
    }

    private void setInteriorColorPickerVisible(){
        interiorColorPicker.visibleProperty().bind(selectedFigure.isLineTool());
        chooseInteriorColorLabel.visibleProperty().bind(selectedFigure.isLineTool());
    }

    private void setCursorCrosschair(){
        drawingWindow.setOnMouseEntered(e ->{
            drawingWindow.setCursor(Cursor.CROSSHAIR);
        });
    }
}


