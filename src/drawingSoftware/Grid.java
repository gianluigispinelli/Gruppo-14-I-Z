package drawingSoftware;

import java.awt.Color;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;



public class Grid {
    private GridPane gridPane;
    private RadioButton gridButton;
    private Slider sliderGrid;
    private Pane drawingWindow;
    private RowConstraints row=new RowConstraints(10);
    private ColumnConstraints col=new ColumnConstraints(10);


    public Grid(Pane drawingWindow,GridPane gridPane,RadioButton gridButton, Slider sliderGrid){
        this.drawingWindow=drawingWindow;
        this.gridButton=gridButton;
        this.gridPane=gridPane;
        this.sliderGrid=sliderGrid;
        
    }
 // function that initializes a Grid in the drawingWindow
    // when the the griButton appear the lines of the grid become visible
    // the grid pane follows the dimension of the drawing window
    public void initializeGrid(){
        gridPane.gridLinesVisibleProperty().bind(gridButton.selectedProperty());
        sliderGrid.visibleProperty().bind(gridButton.selectedProperty());
        gridPane.prefWidthProperty().bind(drawingWindow.prefWidthProperty());
        gridPane.prefHeightProperty().bind(drawingWindow.prefHeightProperty());
        gridPane.disableProperty();
    
        //initialize grid pane
        for(int i = 0; i<250; i++){
            gridPane.getRowConstraints().add(row);
            gridPane.getColumnConstraints().add(col);
    }

   }
    public void modifyGrid(){
       
          //resize the size of the cell
        for(int i=0;i<250;i++){
            row.prefHeightProperty().bind(sliderGrid.valueProperty());
            gridPane.getRowConstraints().set(i,row);
            col.prefWidthProperty().bind(sliderGrid.valueProperty());
            gridPane.getColumnConstraints().set(i, col);
        }
    }




    
}