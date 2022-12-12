package drawingSoftware.Command.LoadAndSaveCommand;

import java.io.IOException;

import drawingSoftware.Command.Command;

public class FileInvoker {
    
    private Command command; 

    public void setCommand(Command command){
        this.command = command; 
    }

    public void executeCommand() throws IOException{
        command.execute();
    }
    
}
