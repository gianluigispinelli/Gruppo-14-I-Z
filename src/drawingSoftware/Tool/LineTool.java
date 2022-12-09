package drawingSoftware.Tool;


import drawingSoftware.Controller;
import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.ShapeCommand.DrawShapeCommand;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class LineTool implements Tool{
    private Controller controller; 
    private Model model; 
    private Pane drawingWindow;

    private Line line;

    private double finalDragX;
    private double startDragX;
    private double finalDragY;
    private double startDragY;
    private ColorPicker borderColor;
    private ColorPicker fillColor;    


    public LineTool(Controller controller, Model model,Pane drawingWindow) {
        this.controller = controller; 
        this.model = model; 
        this.drawingWindow = drawingWindow;

        this.captureMouseEvent();
    }

    @Override
    public void useSelectedTool(ColorPicker borderColor, ColorPicker fillColor) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        this.captureMouseEvent();

        
    }

    private void captureMouseEvent(){

        drawingWindow.setOnMousePressed(e -> {
            
            if(e.getButton() == MouseButton.PRIMARY){
                this.setStartDragX(e.getX());
                this.setStartDragY(e.getY());
            }
            e.consume();
        });
        //event filter because mouse move quickly ad 
        drawingWindow.setOnMouseReleased(e ->{

                if(e.getButton() == MouseButton.PRIMARY){
                    this.setFinalDragX(e.getX());
                    this.setFinalDragY(e.getY());
                    this.createLine();
                    e.consume();
                }
        });
    }

    private void createLine(){
        this.line = new Line();
        this.setDim(startDragX, finalDragX, startDragY, finalDragY);
        this.line.setFill(fillColor.getValue());
        this.line.setStroke(borderColor.getValue());
        this.drawLine();
    }

    public void setDim(double startDragX, double finalDragX, double startDragY, double finalDragY){
        
        finalDragX = finalDragX < 0.0 ? 0.0 : finalDragX;
        startDragX = startDragX < 0.0 ? 0.0 : startDragX;
        finalDragY = finalDragY < 0.0 ? 0.0 : finalDragY;
        startDragY = startDragY < 0.0 ? 0.0 : startDragY;
        
        if(finalDragX > drawingWindow.getMaxWidth()){
            finalDragX = drawingWindow.getMaxWidth();
            drawingWindow.setPrefWidth(finalDragX);

        }else if(finalDragX > drawingWindow.getPrefWidth() && !(finalDragX > drawingWindow.getMaxWidth())){
            drawingWindow.setPrefWidth(finalDragX);
        }

        if(finalDragY > drawingWindow.getMaxHeight()){
            finalDragY = drawingWindow.getMaxHeight();
            drawingWindow.setPrefHeight(finalDragY);
        
        }else if(finalDragY > drawingWindow.getPrefHeight() && !(finalDragY > drawingWindow.getMaxHeight()) ){
                drawingWindow.setPrefHeight(finalDragY);
        }

        this.setXDim(startDragX, finalDragX);
        this.setYDim(startDragY, finalDragY);
    }

    private void setXDim(double xStartValue, double xEndValue){
        this.line.setStartX(xStartValue);
        this.line.setEndX(xEndValue);
    }

    private void setYDim(double yValue, double yEndValue){
        this.line.setStartY(yValue);
        this.line.setEndY(yEndValue);  
    }

    private void drawLine(){
        DrawShapeCommand drawCommand = new DrawShapeCommand(model,this.line);
        controller.executeCommand(drawCommand);
    }

    @Override
    public ObservableBooleanValue isNotLineTool() {
        ObservableBooleanValue visible = new SimpleBooleanProperty(false);
        return visible;
    }


    public void setFinalDragX(double finalDragX) {
        this.finalDragX = finalDragX;
    }

    public void setStartDragX(double startDragX) {
        this.startDragX = startDragX;
    }

    public void setFinalDragY(double finalDragY) {
        this.finalDragY = finalDragY;
    }

    public void setStartDragY(double startDragY) {
        this.startDragY = startDragY;
    }

}