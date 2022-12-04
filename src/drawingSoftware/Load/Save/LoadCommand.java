package drawingSoftware.Load.Save;

import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class LoadCommand implements Command{

    private Receiver receiver;
    private FileChooser fc;
    private Pane drawingWindow;
    //TODO: parametri del comando

    public LoadCommand(Receiver receiver, FileChooser fc, Pane drawingWindow) {
        this.receiver = receiver;
        this.fc = fc;
        this.drawingWindow = drawingWindow;
    }

    @Override
    public void execute() {
        receiver.loadOperation(fc, drawingWindow);
    }

}