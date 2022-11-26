package drawingSoftware;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.stage.FileChooser;

public class LoadCommand implements Command{

    private Receiver receiver;
    FileChooser fc;
    GraphicsContext graphicsContext;
    //TODO: parametri del comando

    public LoadCommand(Receiver receiver, FileChooser fc, GraphicsContext graphicsContext) {
        this.receiver = receiver;
        this.fc = fc;
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void execute() {
        receiver.loadOperation(fc, graphicsContext);
    }

}
