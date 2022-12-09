package drawingSoftware;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class SliderZoom{

    private Slider slider;

    public SliderZoom(Slider zoomSlider) {
        this.slider = zoomSlider;    
        this.slider.setShowTickMarks(true);
        this.slider.setSnapToTicks(true);
    }

    public void setLabelOfSlider(Label valueZoomSlider){

        this.slider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double valueZoom = (double)newValue;
                valueZoomSlider.setText(Integer.toString((int)(valueZoom*100)) + "%");
            }
            
        });
    }
    public Slider getSlider(){
        return this.slider;
    }
}
