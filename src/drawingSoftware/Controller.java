package drawingSoftware;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import drawingSoftware.Command.Command;
import drawingSoftware.Command.CommandHistory;
import drawingSoftware.Command.BackupCommand.BackupCommand;
import drawingSoftware.Command.BackupCommand.UndoCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.CopyCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.CutCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.DeleteCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.EditorAbstractCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.PasteCommand;
import drawingSoftware.Command.LoadAndSaveCommand.FileInvoker;
import drawingSoftware.Command.LoadAndSaveCommand.LoadCommand;
import drawingSoftware.Command.LoadAndSaveCommand.Receiver;
import drawingSoftware.Command.LoadAndSaveCommand.SaveCommand;
import drawingSoftware.Editor.Editor;
import drawingSoftware.Shapes.MyBoundingBox;
import drawingSoftware.Tool.EllipseTool;
import drawingSoftware.Tool.LineTool;
import drawingSoftware.Tool.RectangleTool;
import drawingSoftware.Tool.SelectTool;
import drawingSoftware.Tool.SelectedToolContext;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
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

    private SelectedToolContext selectedFigure; 

    /*FILE CHOOSER */
    FileChooser filechooser = new FileChooser();
    Receiver receiver = new Receiver();
    FileInvoker fileInvoker = new FileInvoker();    

    /*
     * Istanza del receiver Editor e dell'EditorInvoker
     */
    Editor editor; 
    CommandHistory commandHistory; 
    Model model; 
    private BackupCommand command; 
    private MyBoundingBox boundingBox;


    public MyBoundingBox getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createEditor();
        setScrollPaneContent();
        addListenerToShapes();
        addListenerToSelectedShape();
        setFileChooser();
        setDefaultColor();    
        setSelectedFigure();
        setInteriorColorPickerVisible();
        setAccelerators();
    }

    public void addListenerToSelectedShape(){
        model.getSelectedProperty().addListener(new ChangeListener<Node>() {
            /*

            * Qui va il codice per mettergli il contorno per la selezione 
            */
            @Override
            public void changed(ObservableValue<? extends Node> arg0, Node oldShapeSelected, Node newShapeSelected) {
                if (boundingBox != null){       /* rimuovo vecchio bounding box */
                    drawingWindow.getChildren().remove(boundingBox);
                }
                if (newShapeSelected != null){                        
                        Bounds bounds = newShapeSelected.getBoundsInLocal(); /* bounds della figura selezionata */
                        boundingBox = new MyBoundingBox(
                            bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight()
                            );
                        drawingWindow.getChildren().add(boundingBox);
                }
            }
        });
    }

    public void setScrollPaneContent(){
        scrollPane.setContent(drawingWindow);
        this.setCursorCrosschair();
    }

    public void setSelectedFigure(){
        selectedFigure = new SelectedToolContext(new RectangleTool(this, model, drawingWindow), drawingWindow, borderColorPicker, interiorColorPicker);
    }

    public void setDefaultColor(){
        borderColorPicker.setValue(Color.BLACK);
        interiorColorPicker.setValue(Color.WHITE);
    }

    public void setFileChooser(){
        /* INIZIALIZZAZIONE VARIABILI PER CARICAMENTO FILE */
        // fc.setInitialDirectory(new File("/home/gianluigi/VSC Workspace/JavaProjects/ProgettoSE/Progetto/src/project"));
        filechooser.setInitialDirectory(new File("."));  // apro cartella corrente
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images", "*.png")); // filechooser mi mostra solo png
    }
    
    public void addListenerToShapes(){
        /*
         * Il controller fa da intermediario tra il model delle figure aggiunte e cosa succede nella view
         */
        model.getAllShapes().addListener(new ListChangeListener<Node>() {

            @Override
            public void onChanged(Change<? extends Node> change) {        /* se modifichiamo la drawingWindow */
                while(change.next()){
                    for (Node node : change.getRemoved()) { /* per i nodi che rimuovo dalla lista del model */
                        drawingWindow.getChildren().remove(node);   /* rimuovo il nodo dai figli della drawingWindow */
                    }
                    for (Node node : change.getAddedSubList()){ /* per i nodi che aggiungo alla lista del model */
                        drawingWindow.getChildren().add(node);  /* aggiungo il nodo ai figli della drawingWindow */
                    }
                }
            }
        });
    }

    /*

    * Creazione delle classi che ereditano da Command 
    */
    public void createEditor(){
        model = new Model();          /* Model dove mi salvo le shapes che aggiungo: rispetto dell'MVC pattern*/
        editor = new Editor(model, drawingWindow);
        /*
        
         * Lista backup dei comandi. 
         * principio di single responsibility: ogni comando svolge una determinata funzione
         * low-coupling: il client (controller) non lavora con i ConcreteCommand ma con l'interfaccia. 
         */
        commandHistory = new CommandHistory();
    }
    
    public void executeCommand(BackupCommand command){
        if (command.execute()){ /* if a command requires a backup via the return value */
            commandHistory.push(command);   /* we insert a backup of the command in the stack */
        }
    }

    public void undoOperation(){
        if (!commandHistory.isEmpty()){
            command = commandHistory.pop();
            model.setCurrentShape(null); /* when i undo an operation I don't care what figure I have selected */
            command.undo();
        }
    }

    @FXML
    void undo(ActionEvent event){
        BackupCommand undoCommand = new UndoCommand(this);
        executeCommand(undoCommand);
    }

    @FXML
    void copyAShape(ActionEvent event) {
        EditorAbstractCommand copyCommand = new CopyCommand(editor);
        executeCommand(copyCommand);
    }

    @FXML
    void cutAShape(ActionEvent event) {
        EditorAbstractCommand cutCommand = new CutCommand(editor);
        executeCommand(cutCommand);
    }

    @FXML
    void pasteAShape(ActionEvent event) {
        EditorAbstractCommand pasteCommand = new PasteCommand(editor);
        executeCommand(pasteCommand);
    }    

    @FXML
    void deleteShape(ActionEvent event){
        DeleteCommand deleteCommand =  new DeleteCommand(editor);
        executeCommand(deleteCommand);
    }
 
    @FXML
    void onEllipseClick(ActionEvent event) {
        selectedFigure.changeTool(new EllipseTool(this, model, drawingWindow));
        setInteriorColorPickerVisible();
    }

    @FXML
    void onLineClick(ActionEvent event) {
        selectedFigure.changeTool(new LineTool(this, model, drawingWindow));
        setInteriorColorPickerVisible();
    }

    @FXML
    void onRectangleClick(ActionEvent event) {
        selectedFigure.changeTool(new RectangleTool(this, model, drawingWindow));
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
    void select(ActionEvent event) {
        selectedFigure.changeTool(new SelectTool(this, model, drawingWindow));
        setInteriorColorPickerVisible();
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

    public void setAccelerators(){
        undoItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        copyItem.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        pasteItem.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
        cutItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        loadItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
    }
}


