package drawingSoftware.Command.LoadAndSaveCommand;

import java.io.IOException;

import drawingSoftware.Model;
import drawingSoftware.Command.Command;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class LoadCommand implements Command{

    private Receiver receiver;
    private FileChooser fc;
    private Model model;
    //TODO: parametri del comando

    public LoadCommand(Receiver receiver, Model model) {
        this.receiver = receiver;
        this.model = model;
    }

    @Override
    public boolean execute() throws IOException {
        receiver.loadOperation(model);
        return false; 
    }

}
