package drawingSoftware;

import drawingSoftware.Editor.Command;

public class FileInvoker {
    
    private Command command; 

    public void setCommand(Command command){
        this.command = command; 
    }

    public void executeCommand(){
        command.execute();
    }
    
}
