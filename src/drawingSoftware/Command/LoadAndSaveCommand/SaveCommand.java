package drawingSoftware.Command.LoadAndSaveCommand;

import drawingSoftware.Command.Command;
import javafx.scene.layout.Pane;

public class SaveCommand implements Command {
    private Receiver receiver;
    private Pane screen;
   

    public SaveCommand(Receiver receiver, Pane screen){
        this.receiver = receiver;
        this.screen = screen;
    }

    @Override
    public boolean execute() {
        this.receiver.save(this.screen);    
        return false; 
    }
    
}
