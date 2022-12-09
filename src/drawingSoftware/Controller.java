package drawingSoftware;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import java.io.File;
import java.lang.reflect.Array;
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
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.swing.text.Position;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    
    @FXML
    private Label widthLabel;

    @FXML
    private TextField widthTextField;
    
    @FXML
    private Label heightLabel;

    @FXML
    private TextField heightTextField;
    
    @FXML
    private Slider zoomSlider;

    @FXML
    private Label valueZoomSlider;

    private Alert a;
    private SelectedToolContext selectedFigure; 


    /*FILE CHOOSER */
    FileChooser filechooser = new FileChooser();
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
        addListenerToShapes();
        addListenerToSelectedShape();
        setFileChooser();
        setDefaultColor();    
        setSelectedFigure();
        setInteriorColorPickerVisible();
        setAccelerators();
    
        
        //scrollPane.setContent(drawingWindow);
        this.setCursorCrosschair();
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
         
        borderColorPicker.setValue(Color.BLACK);
        interiorColorPicker.setValue(Color.WHITE);
        
        Group elementGroup = new Group();
        elementGroup.getChildren().add(drawingWindow);
        scrollPane.setContent(elementGroup);

        widthTextField.textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // TODO Auto-generated method stub
                
                if (!newValue.matches("([0-9]+[.]?[0-9]{0,2})")) {
                    if(newValue.length()<2){
                        widthTextField.setText("");

                    }else{
                        widthTextField.setText(oldValue);
                    }
                }
            }
        });

        heightTextField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                if (!newValue.matches("([0-9]+[.]?[0-9]{0,2})")) {
                    if(newValue.length()<2){
                        heightTextField.setText("");

                    }else{
                        heightTextField.setText(oldValue);
                }
            }
        }
        });

        a = new Alert(AlertType.NONE);
        
        //to edit 
        widthTextField.setText("0.1");
        heightTextField.setText("0.1");

        widthTextField.setVisible(true);
        heightTextField.setVisible(true);
        widthLabel.setVisible(true);
        heightLabel.setVisible(true);

        SliderZoom sliderZoom = new SliderZoom(zoomSlider);
        sliderZoom.setLabelOfSlider(valueZoomSlider);

        drawingWindow.scaleXProperty().bind(sliderZoom.getSlider().valueProperty());
        drawingWindow.scaleYProperty().bind(sliderZoom.getSlider().valueProperty());
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
       

        // selectedFigure = new SelectedToolContext(new RectangleTool(model, drawingWindow), drawingWindow, borderColorPicker, interiorColorPicker);
        // setInteriorColorPickerVisible();
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
        editor = new Editor(model);
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

    @FXML
    void editShape(ActionEvent event) {
     /*
      * disable text field when shape is not selected.
      */
    double valueTextFieldWight = 0.0;
    double valueTextFieldHeight = 0.0;
    Shape shape = (Shape) (drawingWindow.lookup("#selected"));
    
    if(!heightTextField.getText().contentEquals("") && !widthTextField.getText().contentEquals("")){
        valueTextFieldWight = Double.parseDouble(widthTextField.getText());
        valueTextFieldHeight = Double.parseDouble(heightTextField.getText());
    }
    if(valueTextFieldWight == 0.0 || valueTextFieldHeight == 0.0){
        a.setAlertType(AlertType.ERROR);
        a.setContentText("Inserted 0.0.\nTry with value greater than zero.");
        a.show();
    }

    else if(shape!=null && valueTextFieldWight > 0.0 && valueTextFieldHeight > 0.0){
        
        if(shape instanceof Rectangle ){
            
            Rectangle rect=(Rectangle) shape;
            double rectangleWidth = approximateDoubleValue(rect.getWidth());
            double rectangleHeight = approximateDoubleValue(rect.getHeight());
            double ratio = rectangleHeight/rectangleWidth;
            double[] coordinates = new double[2];

            if(valueTextFieldWight > drawingWindow.getPrefWidth() || valueTextFieldHeight > drawingWindow.getPrefHeight()){
                coordinates = setDimShape(valueTextFieldWight, valueTextFieldHeight);
                valueTextFieldWight = coordinates[0] - rect.getX();
                valueTextFieldHeight = coordinates[1] - rect.getY();
            }
            
            if(valueTextFieldWight != rectangleWidth && valueTextFieldHeight == rectangleHeight){
                double height = valueTextFieldWight*ratio;
                
                rect.setWidth(valueTextFieldWight);
                rect.setHeight(height);
                
                

            }
            else if(valueTextFieldHeight != rectangleHeight && valueTextFieldWight == rectangleWidth){
                double weight = valueTextFieldWight/ratio;
                rect.setHeight(valueTextFieldHeight);
                rect.setWidth(weight);
                
                
            }
            else{
                rect.setHeight(valueTextFieldHeight);
                rect.setWidth(valueTextFieldWight);
            
            }
            heightTextField.setText(String.valueOf(approximateDoubleValue(rect.getHeight())));
            widthTextField.setText(String.valueOf(approximateDoubleValue(rect.getWidth())));
    }

    
    
    else if(shape instanceof Ellipse){
        Ellipse ellipse = (Ellipse) shape;
        double radiusXEllipse = approximateDoubleValue(ellipse.getRadiusX());
        double radiusYEllipse = approximateDoubleValue(ellipse.getRadiusY());
        double ratio=approximateDoubleValue(radiusYEllipse/radiusXEllipse);
        double[] coordinates = new double[2];

        if(valueTextFieldWight > drawingWindow.getPrefWidth() || valueTextFieldHeight > drawingWindow.getPrefHeight()){
            coordinates = setDimShape(valueTextFieldWight, valueTextFieldHeight);
            valueTextFieldWight = coordinates[0];
            valueTextFieldHeight = coordinates[1];
            ellipse.setCenterX(valueTextFieldWight/2);
            ellipse.setCenterY(valueTextFieldHeight/2);
        }
        if(valueTextFieldWight/2 != radiusXEllipse && valueTextFieldHeight/2 == radiusYEllipse){
            ellipse.setRadiusX(valueTextFieldWight/2);
            ellipse.setRadiusY((valueTextFieldWight/2)*ratio);

            
        
        }else if(valueTextFieldHeight/2 != radiusYEllipse && valueTextFieldWight/2 == radiusXEllipse){
            ellipse.setRadiusY(valueTextFieldHeight/2);
            ellipse.setRadiusX((valueTextFieldHeight/2)/ratio);
            

        }else{

            ellipse.setRadiusY(valueTextFieldHeight/2);
            ellipse.setRadiusX(valueTextFieldWight/2);
            
        }
        heightTextField.setText(String.valueOf(approximateDoubleValue(ellipse.getRadiusY())));
        widthTextField.setText(String.valueOf(approximateDoubleValue(ellipse.getRadiusX())));

    }
    else if(shape instanceof Line){
        // add label
        Line line = (Line) shape;
        double segmentWidth = calculateSegmentValue(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        double a = line.getEndX() - line.getStartX();
        double b = line.getEndY() - line.getStartY();

        double[] coordinates = new double[2];

        if(valueTextFieldWight > drawingWindow.getPrefWidth() || valueTextFieldHeight > drawingWindow.getPrefHeight()){
            coordinates = setDimShape(valueTextFieldWight, valueTextFieldHeight);
            valueTextFieldWight = coordinates[0] - line.getStartX();
            
        }
        double scalarFactor = valueTextFieldWight/segmentWidth;
        double newEndX = line.getStartX() + a*scalarFactor;
        double newEndY = line.getStartY() + b*scalarFactor;
        line.setEndX(newEndX);
        line.setEndY(newEndY);

        widthTextField.setText(String.valueOf(calculateSegmentValue(line.getStartX(), line.getStartY(), newEndX, newEndY)));
        
    }
    }

}

private double calculateSegmentValue(double startX, double startY, double endX, double endY){
    double segmentValue = Math.sqrt(Math.pow((endY - startY), 2) + Math.pow((endX - startX), 2));
    return approximateDoubleValue(segmentValue);
}
private double approximateDoubleValue(double number){
    return Math.round(number*100.00)/100.00;
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

 private double[] setDimShape(double finalX, double finalY){
    finalX = finalX < 0.0 ? 0.0 : finalX;
    finalY = finalY < 0.0 ? 0.0 : finalY;
            

    if(finalX > drawingWindow.getMaxWidth()){
        finalX = drawingWindow.getMaxWidth();
        drawingWindow.setPrefWidth(finalX);

    }else if(finalX > drawingWindow.getPrefWidth() && !(finalX > drawingWindow.getMaxWidth())){
        drawingWindow.setPrefWidth(finalX);
    }

    if(finalY > drawingWindow.getMaxHeight()){
        finalY = drawingWindow.getMaxHeight();
        drawingWindow.setPrefHeight(finalY);
    
    }else if(finalY > drawingWindow.getPrefHeight() && !(finalY > drawingWindow.getMaxHeight())){
        drawingWindow.setPrefHeight(finalY);
    }
    
    double[] coordinates = {finalX, finalY};
    return coordinates;
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




