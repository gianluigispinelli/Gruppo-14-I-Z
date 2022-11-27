package drawingSoftware;

import javafx.scene.canvas.Canvas;

public class SaveCommand implements Command {
    private Receiver receiver;
    private Canvas screen;
   

    public SaveCommand(Receiver receiver, Canvas screen){
        this.receiver = receiver;
        this.screen = screen;
    }

    @Override
    public void execute() {
        this.receiver.save(this.screen);    
    }
    
}
