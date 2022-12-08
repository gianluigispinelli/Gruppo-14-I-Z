package drawingSoftware.Command;

import java.util.Stack;

import drawingSoftware.Command.BackupCommand.BackupCommand;

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
