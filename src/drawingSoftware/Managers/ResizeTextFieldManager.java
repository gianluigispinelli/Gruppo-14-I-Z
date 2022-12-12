package drawingSoftware.Managers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


/*
 * ResizeTextFieldManager main is to manage width and height textField of shape. 
 * Initialize both text fields disable and enabled only if a shape is selected.
 * Both text fields accept only double or integer number and if user inserts value 0.0, it's broken up a allert 
 * that's warned user inserting value greater than 0.0.
 * 
 */

public class ResizeTextFieldManager {
    private TextField widthField;
    private TextField heightField;
    private Label widthLabel;
    private Label heightLabel;
    private Alert allert;

    public ResizeTextFieldManager(TextField widthField, TextField heightTextField, Label widthLabel, Label heightLabel){
        this.widthField = widthField;
        this.heightField = heightTextField;
        this.widthLabel = widthLabel;
        this.heightLabel = heightLabel;
        this.acceptValueTextField();
        this.stateInitialField();
        
        this.allert = new Alert(AlertType.NONE);
    }

    private void stateInitialField(){
        this.widthField.setDisable(true);
        this.heightField.setDisable(true);
    }

    /*
     * Changing label text according to instance of shape 
     * and set text fields value with size of selected shape
     */
    public void checkShapeSelected(Node shapeSelected){
        double width = 0.1;
        double height = 0.1;
        Shape shape = (Shape)shapeSelected;
        
        if(shape instanceof Rectangle){
            this.widthLabel.setText("Width");
            this.heightLabel.setText("Height");
            width = ((Rectangle)shape).getWidth();
            height = ((Rectangle)shape).getHeight();
        }
        else if(shape instanceof Ellipse){
            this.widthLabel.setText("Radius X");
            this.heightLabel.setText("Radius Y");
            width = ((Ellipse)shape).getRadiusX();
            height = ((Ellipse)shape).getRadiusY();
        }
        else if(shape instanceof Line){
            this.widthLabel.setText("Width");
            this.heightLabel.setText("Height");
            Line line = (Line)shape;
            width = calculateSegmentValue(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
            this.heightField.setDisable(true);
        }
        this.widthField.setText(String.valueOf(approximateDoubleValue(width)));
        this.heightField.setText(String.valueOf(approximateDoubleValue(height)));
        
    }
    public double setValueFieldHeight(double valueField){
        if(!this.heightField.getText().contentEquals("") ){
            valueField = Double.parseDouble(this.heightField.getText());
        }
        if(checkValueField(valueField)){
            return 0.0;
        }
        return valueField;
    }
    public double setValueFieldWidth(double valueField){
        if(!this.widthField.getText().contentEquals("")){
            valueField = Double.parseDouble(this.widthField.getText());
        }
        if(checkValueField(valueField)){
            return 0.0;
        }
        return valueField;
    }

    private boolean checkValueField( double valueTextField){
        if(valueTextField == 0.0){
            this.allert.setAlertType(AlertType.ERROR);
            this.allert.setContentText("Inserted Invalid Value.\nTry with value greater than zero.");
            this.allert.show();
            return true;
        }
        return false;
    }


    private double calculateSegmentValue(double startX, double startY, double endX, double endY){
        double segmentValue = Math.sqrt(Math.pow((endY - startY), 2) + Math.pow((endX - startX), 2));
        return approximateDoubleValue(segmentValue);
    }
    private double approximateDoubleValue(double number){
        return Math.round(number*100.00)/100.00;
    }
    
    private void acceptValueTextField(){
        checkValueHeightField();
        checkValuewidthField();
    }

    /*
     * Checking value insert in the text field with regular expression, that accepts only double or int value.
     * 
     */
    private void checkValueHeightField(){
        this.heightField.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                if (!newValue.matches("(^[0-9]{0,4}[.]?[0-9]{0,2})")) {
                    if(newValue.length()<2){
                        heightField.setText("");

                    }else{
                        heightField.setText(oldValue);
                }
                if(newValue.length() > 4){}
            }
        }
        });
       
    }

    private void checkValuewidthField(){
        this.widthField.textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("([0-9]{0,3}[.]?[0-9]{0,2})")) {
                    if(newValue.length()<2){
                        widthField.setText("");

                    }else{
                        widthField.setText(oldValue);
                    }
                }
            }
        });
    }
    public void setDisableFieldWidth(boolean value){
        this.widthField.setDisable(value);
    }
    
    public void setDisableFieldHeight(boolean value){
        this.heightField.setDisable(value);
    }

    public void setFieldWight(double value){
        this.widthField.setText(String.valueOf(value));
    }
    
    public void setFieldHeight(double value){
        this.heightField.setText(String.valueOf(value));
    }
}
