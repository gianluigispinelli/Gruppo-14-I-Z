package drawingSoftware;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import drawingSoftware.Command.Command;
import drawingSoftware.Command.CommandHistory;
import drawingSoftware.Command.BackupCommand.BackupCommand;
import drawingSoftware.Command.BackupCommand.UndoCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.CopyCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.CutCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.DeleteCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.EditShapeCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.EditorAbstractCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.PasteCommand;
import drawingSoftware.Command.LoadAndSaveCommand.FileInvoker;
import drawingSoftware.Command.LoadAndSaveCommand.LoadCommand;
import drawingSoftware.Command.LoadAndSaveCommand.Receiver;
import drawingSoftware.Command.LoadAndSaveCommand.SaveCommand;

import drawingSoftware.Editor.Editor;
import drawingSoftware.Managers.ContextMenuManager;
import drawingSoftware.Managers.ResizeTextFieldManager;
import drawingSoftware.Shapes.MyBoundingBox;
import drawingSoftware.Tool.EllipseTool;
import drawingSoftware.Tool.LineTool;
import drawingSoftware.Tool.PolygonTool;
import drawingSoftware.Tool.RectangleTool;
import drawingSoftware.Tool.SelectTool;
import drawingSoftware.Tool.SelectedToolContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Controller implements Initializable{

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
    
    @FXML
    private Label widthLabel;

    @FXML
    private TextField widthTextField;
    
    @FXML
    private Label heightLabel;
    
    @FXML
    private Button polygonButton; 

    @FXML
    private TextField heightTextField;
    
    @FXML
    private Slider zoomSlider;

    @FXML
    private Label valueZoomSlider;

    @FXML
    private GridPane gridPane;

    @FXML
    private Slider sliderGrid;

    @FXML
    private RadioButton gridButton;

    private SelectedToolContext selectedFigure; 
    private  ResizeTextFieldManager textFieldManager;
    private ShapeListener listenerManager;
    private ContextMenuManager contextMenuManager;
 

    Receiver receiver = new Receiver();
    FileInvoker fileInvoker = new FileInvoker();    
    /*
     * Istanza del receiver Editor e dell'EditorInvoker
     */
    private Editor editor; 
    CommandHistory commandHistory;
    private Model model; 
    private BackupCommand command;
    private MyBoundingBox boundingBox;

    public Editor getEditor() {
        return editor;
    }

    public void setCommandHistory(CommandHistory commandHistory) {
        this.commandHistory = commandHistory;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public MyBoundingBox getBoundingBox() {
        return boundingBox;
    }

    public BackupCommand getCommand() {
        return command;
    }

    public Model getModel() {
        return model;
    }

    public CommandHistory getCommandHistory() {
        return commandHistory;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createEditor();
        setScrollPaneContent();
        setDefaultColor();    
        setSelectedFigure();
        setInteriorColorPickerVisible();
        setAccelerators();
        setSliderZoom();
        addClip();
        setCursorCrosschair();
        createFieldManager();
        createListenerManager();
        //Enable context menu in the drawingWindow
        createContextMenuManager();
    } 

    private void createFieldManager(){
        textFieldManager = new ResizeTextFieldManager(widthTextField, heightTextField, widthLabel, heightLabel);
    }

    private void createListenerManager(){
        this.listenerManager = new ShapeListener(model,boundingBox,drawingWindow, textFieldManager);
        this.listenerManager.addListenerToShapes();
        this.listenerManager.addListenerToSelectedShape();
    }

    private void createContextMenuManager(){
        contextMenuManager = new ContextMenuManager(this);
        contextMenuManager.setContextMenu();
    }

    private void setSliderZoom(){
        SliderZoom sliderZoom = new SliderZoom(zoomSlider);
        sliderZoom.setLabelOfSlider(valueZoomSlider);

        drawingWindow.scaleXProperty().bind(sliderZoom.getSlider().valueProperty());
        drawingWindow.scaleYProperty().bind(sliderZoom.getSlider().valueProperty());
    }

    private void addClip(){
        Rectangle rect = new Rectangle(0, 0, 10, 10);
        rect.widthProperty().bind(drawingWindow.widthProperty());
        rect.heightProperty().bind(drawingWindow.heightProperty());
        drawingWindow.setClip(rect);
    }

    public void setScrollPaneContent(){
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
        Group elementGroup = new Group();
        elementGroup.getChildren().add(drawingWindow);
        scrollPane.setContent(elementGroup);
    }

    public void setSelectedFigure(){
        selectedFigure = new SelectedToolContext(new EllipseTool(this, model, drawingWindow, borderColorPicker, interiorColorPicker), drawingWindow);
        setInteriorColorPickerVisible();
    }

    public void setDefaultColor(){
        borderColorPicker.setValue(Color.BLACK);
        interiorColorPicker.setValue(Color.WHITE);
    }
    

    public void createEditor(){
        model = Model.getInstance();  
        editor = new Editor(model, drawingWindow);
        /*
        
         * List of command backups 
         * single responsibility principle: every command does a specif functionality
         * low-coupling: the client (controller) doesn't communicate with the ConcreteCommand but with the interface 
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
    void deleteShape(ActionEvent event){
        DeleteCommand deleteCommand =  new DeleteCommand(editor);
        executeCommand(deleteCommand);
    }
 
    @FXML
    void onEllipseClick(ActionEvent event) {
        selectedFigure.changeTool(new EllipseTool(this,model, drawingWindow, borderColorPicker,interiorColorPicker));
        setInteriorColorPickerVisible();
    }

    @FXML
    void onLineClick(ActionEvent event) {
        selectedFigure.changeTool(new LineTool(this, model, drawingWindow, borderColorPicker));
        setInteriorColorPickerVisible();
    }

    @FXML
    void onRectangleClick(ActionEvent event) {

        selectedFigure.changeTool(new RectangleTool(this, model, drawingWindow, borderColorPicker,interiorColorPicker));
        setInteriorColorPickerVisible();
    }
    @FXML
    void onPolygonClick(ActionEvent event) {
        selectedFigure.changeTool(new PolygonTool(this, model, drawingWindow, borderColorPicker,interiorColorPicker));
        setInteriorColorPickerVisible();
    }

    @FXML
    void onLoad(ActionEvent event) throws IOException {
        Command command = new LoadCommand(receiver, model);
        fileInvoker.setCommand(command);
        fileInvoker.executeCommand();
    }

    @FXML
    void onSave(ActionEvent event) throws IOException {
        SaveCommand saveCommand = new SaveCommand(this.receiver,drawingWindow);
        this.fileInvoker.setCommand(saveCommand);
        this.fileInvoker.executeCommand();
    } 

    @FXML
    void select(ActionEvent event) {
        selectedFigure.changeTool(new SelectTool(this, model, drawingWindow, borderColorPicker, interiorColorPicker));
        setInteriorColorPickerVisible();
    }    

    @FXML
    void editShape(ActionEvent event) {
     /*
      * disable text field when shape is not selected.
      */
        EditShapeCommand editShapeCommand = new EditShapeCommand(editor, textFieldManager, this.listenerManager.getBoundingBox());
        this.executeCommand(editShapeCommand);
    }


    private void setCursorCrosschair(){
        drawingWindow.setCursor(Cursor.CROSSHAIR);
    }

    private void setAccelerators(){
        undoItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        loadItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
    }
    private void setInteriorColorPickerVisible(){
        interiorColorPicker.visibleProperty().bind(selectedFigure.isLineTool());
        chooseInteriorColorLabel.visibleProperty().bind(selectedFigure.isLineTool());
    }
    @FXML
    void enableGrid(ActionEvent event) {
        Grid gr = new Grid(drawingWindow, gridPane, gridButton, sliderGrid);
        gr.initializeGrid();
        gr.modifyGrid();
    }

    public Pane getDrawingWindow() {
        return drawingWindow;
    }
}





