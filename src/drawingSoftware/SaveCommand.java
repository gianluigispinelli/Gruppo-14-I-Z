package drawingSoftware;

import javafx.scene.layout.Pane;

public class SaveCommand implements Command {
    private Receiver receiver;
    private Pane screen;
   

    public SaveCommand(Receiver receiver, Pane screen){
        this.receiver = receiver;
        this.screen = screen;
    }

    @Override
    public void execute() {
        this.receiver.save(this.screen);    
    }
    
}
