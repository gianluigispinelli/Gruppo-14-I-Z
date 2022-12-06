package drawingSoftware;

import java.util.Stack;

import drawingSoftware.Editor.BackupCommand;

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
}
