package drawingSoftware;

public class EditorInvoker {

    private Command command;  

    public void setCommand(Command command){
        this.command = command; 
    }

    public void executeCommand(){
        command.execute();
    }
}
