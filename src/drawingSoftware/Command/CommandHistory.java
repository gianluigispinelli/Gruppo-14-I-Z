package drawingSoftware.Command;

import java.util.Stack;

import drawingSoftware.Command.BackupCommand.BackupCommand;

/*

 * CommandHistory is a class whose goal is to have a backup of all the executed commands from the
 * opening of the application. Every command who needs to be tracked inherits from the BackupCommand abstract class.
 * This class is made up of a stack in which the istances of the commands are going to be pushed. 
 */

public class CommandHistory {
    Stack<BackupCommand> history;

    public CommandHistory(){
        history = new Stack<>();
    }

    public void push(BackupCommand c){
        history.push(c);
    }

    public BackupCommand pop(){
        return history.pop();
    }

    public boolean isEmpty(){
        return history.isEmpty();
    }
}
